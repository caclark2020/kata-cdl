package uk.bcb.cdl.kata.shopping.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 *
 */
public class Order 
{
	private Map<String, Integer> entries = new HashMap <> ();
	
	private Catalogue catalogue;
	private PricePlan priceplan;
	
	public static record OrderEntry (String sku, int amount) {};
	
	public Order(Catalogue catalogue, PricePlan priceplan) 
	{
		this.catalogue = catalogue;
		this.priceplan = priceplan;
	}

	public final void add (String sku, int amount)
	{
		if (!this.catalogue.contains(sku))
			throw new IllegalArgumentException ("No such product: '"+sku+"'");
		if (amount<1) 
			throw new IllegalArgumentException("Amount must be 1 or above: value was "+amount);
		entries.put(sku, entries.containsKey(sku) ? entries.get(sku)+amount : amount);
	}
	
	public final void remove (String sku, int amount)
	{
		if (!this.catalogue.contains(sku))
			throw new IllegalArgumentException ("No such product: '"+sku+"'");
		if (amount<1) 
			throw new IllegalArgumentException("Amount must be 1 or above: value was "+amount);
		if (!entries.containsKey(sku)) return;
		int currentAmount = entries.get(sku);
		if (amount >= currentAmount)
			entries.remove(sku);
		else
			entries.put(sku, currentAmount-amount);
	}

	public final void clear (String sku)
	{
		// (1) Check if SKU is in catalogue
		if (!this.catalogue.contains(sku))
			throw new IllegalArgumentException ("No such product: '"+sku+"'");

		// (2) Do nothing if there are no orders for the given SKU.
		if (!entries.containsKey(sku)) return;

		// (3) Remove the entry for the SKU.
		entries.remove(sku);
	}

	public final void clear ()
	{
		// clear all entries
		entries.clear();
	}

	public final List<OrderEntry> getTotals ()
	{
		List<OrderEntry> list = new ArrayList<> ();
		entries.entrySet().stream().sorted (Map.Entry.<String,Integer>comparingByKey()).forEach (
			e -> {list.add(new OrderEntry (e.getKey(), e.getValue()));});
		return list;
	}
	
	public final int getTotal ()
	{
		// Return the sum of the amounts
		return entries.values().stream().mapToInt(Integer::intValue).sum();
	}
	
	public final int getTotal (String sku)
	{
		// (1) Check if SKU is in catalogue
		if (!this.catalogue.contains(sku))
			throw new IllegalArgumentException ("No such product: '"+sku+"'");

		// (2) Return 0 if there are no orders for the given SKU.
		if (!entries.containsKey(sku)) return 0;

		return entries.get(sku);
	}

	public final PricePlan getPricePlan ()
	{
		return this.priceplan;
	}

	public final Catalogue getCatalogue ()
	{
		return this.catalogue;
	}
}
