CDL Shopping Kata
=================

This is a code Kata for [CDL](http://www.cdl.co.uk/): Here are the functional requirements:

 1. Implement the code for a checkout system that handles pricing schemes such as apples cost 50 pence, three apples cost £1.30.
 2. Implement the code for a supermarket checkout that calculates the total price of a number of items. In a normal supermarket, things are identified using Stock Keeping Units, or SKUs. In our store, we'll use individual letters of the alphabet (A, B, C, and so on). Our goods are priced individually. 
 3. In addition, some items are multi-priced: buy n of them, and they'll cost you y pence. For example, item A might cost 50 pence individually, but this week we have a special offer: buy three As and they'll cost you £1.30. 
 4. Use these prices for the sample:

	Item	Unit Price (pence)	Special Price (pence)
	A	50	3 for 130
	B	30	2 for 45
	C	20	
	D	15	
 5. Our checkout accepts items in any order, so that if we scan a B, an A, and another B, we'll recognize the two Bs and price them at 45 (for a total price so far of 95). Because the pricing changes frequently, we need to be able to pass in a set of pricing rules each time we start handling a checkout transaction.
 6. The solution should allow for items to input at the command line, and allow for a final total to be calculated and for a running total after each item is scanned.

And the non functional requirements:

7. Please use Java for the solution and place this onto a publicly accessible Github repository and let us know where it is.


Running the demo
----------------

You can grab a zip of the main branch of the code, and extract in a directory, and import the project into Eclipse IDE.

This project has been created under [Eclipse IDE for Java Developers 2022-06 (4.24.0)](https://www.eclipse.org/downloads/packages/release/2022-06/r/eclipse-ide-java-developers)
And [Java 18.0.1](https://jdk.java.net/18/), and uses [Apache Maven](https://maven.apache.org/) as the build tool.

To run the demo, just execute the uk.bcb.cdl.kata.shopping.Main class
This will use a standard catalogue and priceplan for the demo, according to the Kata.


Command Line Interface (CLI)
============================

When you run the demo, you will start a command line interface, that allows you to manage your shopping basket (order), look at the catalogue and
priceplan, retrieve a summary of what is in your order, and also get a fully itemized receipt.

Managing your order
-------------------

**add** command:

	This command allows you to add 1 or more items of a specific type to your order,
	for example: 'add A' will add 1 unit of item 'A' to your order, and 'add A 10' will add 10 units of item 'A' to your order. 
	If you specify an amount, it must be a positive integer value greater or equal to 1.
	The item code (SKU) must exist in the catalogue, otherwise there will be an error.
	
**remove** command

	This command allows you to remove 1 or more items of a specific type from your order.
	for example: 'remove A' will remove 1 unit of item 'A' from your order, and 'remove A 10' will remove 10 units of item 'A'
	from your order. 
	If you specify an amount, it must be a positive integer value greater or equal to 1.
	The item code (SKU) must exist in the catalogue, otherwise there will be an error.
	If the amount to remove is greater than the amount you have in your order, then the item will be cleared from your order -
	so for example if you have 8 items of 'B', and you use 'remove B 20', you will end up with 0 Bs in your order.
	
**clear** command

	This command will, if an SKU is specified, clear the total for that specific item in your order, and if an SKU is
	not specified, it will clear the entire order.
	
**summary** command

	This command will provide you a summary of the total amount of items in your order, and the total of each SKU in your order.
	
**receipt** command

	This command will display a full receipt, prices in pounds, including the amount you are saving from offers.
	
Catalogue / Priceplan
---------------------

**catalogue** command

	This will list all the SKUs (Stock Keeping Units) available in the catalogue. For the purposes of the demo, these are
	basically 'A', 'B', 'C' and 'D'.
	
**prices** command

	This will display the base prices of all the SKUs in the catalogue (cost of the item outside any offers)
	
**offers** command

	This will display the offers available from the priceplan (reduced price multibuy offers)
	
Session
-------

**bye** / **exit** / **quit** commands

	These commands will end your session in the CLI (Command Line Interpreter), and close the program.
	