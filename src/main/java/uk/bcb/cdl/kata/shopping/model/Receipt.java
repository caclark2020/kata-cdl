package uk.bcb.cdl.kata.shopping.model;

import java.util.ArrayList;
import java.util.List;

import uk.bcb.cdl.kata.shopping.model.Order.OrderEntry;
import uk.bcb.cdl.kata.shopping.model.PricePlan.BasePrice;
import uk.bcb.cdl.kata.shopping.model.PricePlan.Offer;

/**
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 */
public class Receipt 
{
	public static record ReceiptEntry (
		String sku, int amount, int cost, int savings, int adjustedCost, boolean offer) { };

	private List<ReceiptEntry> entries;	
	private PricePlan priceplan;
	private int totalBeforeSavings = 0;
	private int totalSavings = 0;
	private int totalAfterSavings = 0;
	private int totalItems = 0;
	
	private Receipt (PricePlan priceplan, List<ReceiptEntry> entries)
	{
		this.entries = entries;
		this.priceplan = priceplan;
		
		totalBeforeSavings = 0;
		totalSavings = 0;
		totalAfterSavings = 0;
		totalItems = 0;
		
		// Calculate totals
		entries.forEach(e -> { 
			totalItems += e.amount;
			totalBeforeSavings += e.cost();
			totalAfterSavings += e.adjustedCost();
			totalSavings += e.savings();});
	}

	public static final Receipt calculate (Order order)
	{
		PricePlan priceplan = order.getPricePlan();
		List<ReceiptEntry> receiptEntries = new ArrayList<>();
		for (OrderEntry entry: order.getTotals())
		{
			ReceiptEntry receiptEntry;
			// Offer on the catalogue item (can be null if there is no offer)
			Offer offer = priceplan.getOffer(entry.sku());
			// Base price
			BasePrice basePrice = priceplan.getBasePrice(entry.sku());
			// Price without savings
			int baseCost = entry.amount() * basePrice.pence();

			if (offer==null)
				// There are no offers on this SKU, so savings are 0, and total is the same as the subtotal.
				receiptEntry = new ReceiptEntry (entry.sku(), entry.amount(), baseCost, 0, baseCost, false);
			else
			{
				int total = 
					( (entry.amount() / offer.amount())* offer.pence() )+
					( (entry.amount() % offer.amount())* basePrice.pence() );
				receiptEntry = new ReceiptEntry (entry.sku(), entry.amount(), baseCost, baseCost-total, total, true);
			}
			receiptEntries.add(receiptEntry);
		}
		
		return new Receipt (priceplan, receiptEntries);
	}

	public final void print ()
	{
		System.out.println("Your receipt:");
		System.out.println("~~~~~~~~~~~~~");
		System.out.println();
		System.out.println("""
--------+------------+---------+------------+------------+----------
Item    | Unit Price |  Amount |  Subtotal  |   Savings  |  Total
--------+------------+---------+------------+------------+----------""");


		
		for (ReceiptEntry entry: entries)
		{
			System.out.printf(
				// Format specifier to align all the values within the columns	
				"%1$-8s|%2$12s|%3$9s|%4$12s|%5$12s|%6$10s%n",
				// Column 1: SKU (catalogue item identifier)
				entry.sku(), 
				// Column 2: Unit price for SKU (taken from the base price in the priceplan)
				PriceFormatter.formatPence(priceplan.getBasePrice(entry.sku).pence()), 
				// Column 3: Number of the catalogue items currently in the order.
				""+entry.amount(), 
				// Column 4: 
				PriceFormatter.formatPence(entry.cost()), 
				PriceFormatter.formatPence(entry.savings()), 
				PriceFormatter.formatPence(entry.adjustedCost()));
		}
		
		System.out.println();
		System.out.println("Total items purchased: "+totalItems);
		System.out.println("Total before savings:  "+PriceFormatter.formatPence(totalBeforeSavings));
		System.out.println("Total after savings:   "+PriceFormatter.formatPence(totalAfterSavings));
		System.out.println("Savings:               "+PriceFormatter.formatPence(totalSavings));
		System.out.println();
	}
	
	// Just a little tester...
	public static void main(String[] args) {
		/*
		 * ============================================================================================
		 * Set up the demo catalogue and priceplan.
		 * ============================================================================================
		 */
				Catalogue catalogue = new InMemoryCatalogueImpl("A", "B", "C", "D");

				InMemoryPricePlanImpl priceplan = new InMemoryPricePlanImpl();

				// Add base prices for SKUs 'A' to 'D'
				priceplan.add(new PricePlan.BasePrice("A", 50));
				priceplan.add(new PricePlan.BasePrice("B", 30));
				priceplan.add(new PricePlan.BasePrice("C", 20));
				priceplan.add(new PricePlan.BasePrice("D", 15));
				
				// Add offers for SKUs 'A' and 'B'
				priceplan.add(new PricePlan.Offer("A",3, 130));
				priceplan.add(new PricePlan.Offer("B",2, 45));

		/*
		 * ============================================================================================
		 * Create a new order object based on the current catalogue and priceplan.
		 * ============================================================================================
		 */
				Order order = new Order (catalogue, priceplan);

				order.add("A", 22);
				order.add("B", 7);
				order.add("C", 5);
				order.add("D", 4);

				Receipt receipt = Receipt.calculate(order);
				receipt.print();
	}	
}
