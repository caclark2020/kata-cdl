package uk.bcb.cdl.kata.shopping;

import uk.bcb.cdl.kata.shopping.cli.CommandInterpreter;
import uk.bcb.cdl.kata.shopping.cli.Console;
import uk.bcb.cdl.kata.shopping.cli.Logo;
import uk.bcb.cdl.kata.shopping.model.Catalogue;
import uk.bcb.cdl.kata.shopping.model.InMemoryCatalogueImpl;
import uk.bcb.cdl.kata.shopping.model.InMemoryPricePlanImpl;
import uk.bcb.cdl.kata.shopping.model.Order;
import uk.bcb.cdl.kata.shopping.model.PricePlan;

/**
 * Main application class of the CDL Kata demo.
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 */
public class Main {

	public static void main(String[] args) {

/*
 * ============================================================================================
 * Welcome message
 * ============================================================================================
 */
		Logo.showLogo();
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

/*
 * ============================================================================================
 * Create a console interface, and kick off the session.
 * ============================================================================================
 */
		Console console = new Console ();
		CommandInterpreter commandInterpreter = new CommandInterpreter(console, order);

		boolean cont = true;
		while (cont)
		{
			console.print (">> ");
			String line = console.readLine();		
			if (line.trim().equalsIgnoreCase("quit"))
			{
				cont = false;
			}
			else
			{
				commandInterpreter.handleCommand(line);
			}
			
		}

		System.out.println("--------------------------------------------------------------");
		System.out.println("Session finished.");
	}

}
