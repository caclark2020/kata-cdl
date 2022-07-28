package uk.bcb.cdl.kata.shopping.cli;

import java.util.Arrays;

import uk.bcb.cdl.kata.shopping.model.Order;
import uk.bcb.cdl.kata.shopping.model.PricePlan.BasePrice;
import uk.bcb.cdl.kata.shopping.model.PricePlan.Offer;
import uk.bcb.cdl.kata.shopping.model.Receipt;

/**
 * <p>The command interpreter for the CDL shopping list kata.</p>
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 *
 */
public class CommandInterpreter 
{
	private Console console;
	private Order order;
	
	/**
	 * Rudimentary command list, as we are not implementing a full console command API here.
	 * In the future, you could expand this of course to create a more expansive API where commands could be declared
	 * as individual objects, using Java annotations.
	 */
	public static final void showCommandList ()
	{
		System.out.println();
		System.out.println("Command List:");
		System.out.println("=============");
		System.out.println();
		System.out.println("Order management:");
		System.out.println("-----------------");
		System.out.println("clear:                 Clear all items in the current order");
		System.out.println("clear <SKU>:           Clear order amount for specific item in the current order");
		System.out.println("add <SKU>:             Add 1 item <SKU> to the current order");
		System.out.println("add <SKU> <AMT>:       Add <AMT> items <SKU> to the current order");
		System.out.println("remove <SKU>:          Remove 1 item <SKU> from the current order");
		System.out.println("remove <SKU> <AMT>:    Remove <AMT> items <SKU> from the current order");
		System.out.println("summary:               Summary of amounts in the order per catalogue item, and total items");
		System.out.println("receipt:               Detailed receipt for current order (prettified)");
		System.out.println();

		System.out.println("Catalogue/Priceplan:");
		System.out.println("--------------------");
		System.out.println("prices:                List all catalogue items, and their base prices");
		System.out.println("offers:                List all multi-buy offers for catalogue items");
		System.out.println("catalogue:             List all catalogue items (by SKU)");
		System.out.println();
		System.out.println("Session:");
		System.out.println("--------");
		System.out.println("quit / exit / bye:     end session");
		System.out.println();
	}
	
	public final void handleCommand(String cmd) {
		if (cmd == null) return;
		if (cmd.isBlank() || cmd.isEmpty()) return;

		String [] subcommands = cmd.split(" ");
		switch (subcommands[0]) 
		{
		// 'quit' / 'exit' commands, which end the session.
		case "quit", "exit", "bye":
			console.println("----------------------------------------------------------------");
			console.println("Session ended with command: '"+subcommands[0]+"'.");
			System.exit(0);
		case "help":
			showCommandList();
			break;
		// 'catalogue' command, that lists all the products by name.
		case "catalogue":
			console.println ();
			console.println ("Available products in catalogue:");
			console.println ("--------------------------------");
			String availItems = Arrays.toString(order.getCatalogue().getAll());
			console.println (availItems.substring(1, availItems.length()-1));
			console.println ();
			break;
		// 'prices' command, that lists the base prices from the priceplan.
		case "prices":
			console.println ();
			console.println ("Pricing details:");
			console.println ("----------------");
			for (BasePrice price: order.getPricePlan().getAllBasePrices())
				System.out.println(price.toPrettyString());
			console.println ();
			break;
		// 'offers' command, that lists the currently available offers from the priceplan.
		case "offers":
			console.println ();
			console.println ("Offers:");
			console.println ("-------");
			for (Offer offer: order.getPricePlan().getAllOffers())
				System.out.println(offer.toPrettyString());
			console.println ();
			break;
		// 'add' command, that adds 1 or more items to the order.
		case "add":
			if (subcommands.length==2)
			{
				try {
					order.add(subcommands[1], 1);
					console.println ("1 '"+subcommands[1]+"' item added to your basket, total now: "+order.getTotal(subcommands[1]));
					console.println ();
				} catch (Exception e) {
					console.error("ERROR: "+e.getMessage());
				}
			}
			else
			{
				try {
					order.add(subcommands[1], Integer.parseInt(subcommands[2]));
					console.println (subcommands[2]+" '"+subcommands[1]+"' items added to your basket, total now: "+order.getTotal(subcommands[1]));
					console.println ();
				} catch (Exception e) {
					console.error("ERROR: "+e.getMessage());
				}
			}
			break;
		// 'remove' command, that removes 1 or more items from the order.
		case "remove":
			if (subcommands.length==2)
			{
				try {
					order.remove(subcommands[1], 1);
					console.println ("1 '"+subcommands[1]+"' item removed from your basket, total now: "+order.getTotal(subcommands[1]));
					console.println ();
				} catch (Exception e) {
					console.error("ERROR: "+e.getMessage());
				}
			}
			else
			{
				try {
					order.remove(subcommands[1], Integer.parseInt(subcommands[2]));
					console.println (subcommands[2]+" '"+subcommands[1]+"' items removed from your basket, total now: "+order.getTotal(subcommands[1]));
					console.println ();
				} catch (Exception e) {
					console.error("ERROR: "+e.getMessage());
				}
			}
			break;
		case "receipt":
			console.println();
			Receipt receipt = Receipt.calculate(order);
			receipt.print();
			break;
		case "summary":
			console.println ();
			console.println ("Summary:");
			console.println ("--------");
			console.println ("You have "+order.getTotal()+ " items currently in your basket:");
			console.println ();
			order.getTotals().forEach(e -> { console.println (e.sku() +": "+e.amount()); } ); 
			console.println ();
			break;
		case "clear":
			if (subcommands.length>1)
			{
				try {
					order.clear(subcommands[1]);
					console.println ("all '"+subcommands[1] +"' items are removed from your basket.");
					console.println ();
				} catch (Exception e) {
					console.error("ERROR: "+e.getMessage());
				}

				
				String sku = subcommands[1];
				order.clear(sku);
				
			}
		default:
			console.error("Unknown command: "+subcommands[0]);
			console.println();
		}
	}

	public CommandInterpreter (
		Console console,
		Order order)
	{
		this.console= console;
		this.order = order;
	}
}
