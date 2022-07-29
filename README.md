CDL Shopping Kata
=================

This is a code Kata for [CDL](http://www.cdl.co.uk/): Here are the functional requirements:

 1. Implement the code for a checkout system that handles pricing schemes such as “apples cost 50 pence, three apples cost £1.30.”
 2. Implement the code for a supermarket checkout that calculates the total price of a number of items. In a normal supermarket, things are identified using Stock Keeping Units, or SKUs. In our store, we’ll use individual letters of the alphabet (A, B, C, and so on). Our goods are priced individually. 
 3. In addition, some items are multi-priced: buy ‘n’ of them, and they’ll cost you ‘y’ pence. For example, item ‘A’ might cost 50 pence individually, but this week we have a special offer: buy three ‘A’s and they’ll cost you £1.30. 
 4. Use these prices for the sample:

	Item	Unit Price (pence)	Special Price (pence)
	A	50	3 for 130
	B	30	2 for 45
	C	20	
	D	15	
 5. Our checkout accepts items in any order, so that if we scan a B, an A, and another B, we’ll recognize the two B’s and price them at 45 (for a total price so far of 95). Because the pricing changes frequently, we need to be able to pass in a set of pricing rules each time we start handling a checkout transaction.
 6. The solution should allow for items to input at the command line, and allow for a final total to be calculated and for a running total after each item is ‘scanned’.

And the non functional requirements:

7. Please use Java for the solution and place this onto a publicly accessible Github repository and let us know where it is.


Running the demo
----------------

You can grab a zip of the main branch of the code, and extract in a directory, and import the project into Eclipse IDE.

This project has been created under [Eclipse IDE for Java Developers 2022-06 (4.24.0)](https://www.eclipse.org/downloads/packages/release/2022-06/r/eclipse-ide-java-developers)
And [Java 18.0.1](https://jdk.java.net/18/), and uses [Apache Maven](https://maven.apache.org/) as the build tool.

To run the demo, just execute the uk.bcb.cdl.kata.shopping.Main class
This will use a standard catalogue and priceplan for the demo, according to the Kata.


Thoughts
--------

1. NFRs:
This was an interesting challenge, as it was a coding challenge out of the blue, and very easy to implement the functional requirements
due to their simplicity. The complexity is in controlling your excitement into blowing the requirements into extrastellar proportions. I could have
gone haywire and started implementing a whole exception handling framework, a GUI console, logging and configuration, and without a company
backing me up, and a specific client to work for, it was about controlling your impulses and sticking to the requirements, so a good challenge.
2. Customer facing:
I have used shopping baskets and online shopping, and this project made me consider about how the whole online shopping architecture works, as
it can be TOP-DOWN (customer-based) or BOTTOM-UP (company based). For example, for a customer's perspective, you make a purchase based on a
specific timestamp, based on a specific catalogue, and a specific priceplan. The customer, if there were any problems, would go back to a company,
whose catalogue is ever-changing, priceplans ever-changing, and ask to focus on a specific order. So, you would say, from a customer perspective,
their **data** is based on a specific catalogue, and a specific priceplan, as well as a specific order. This would mean that catalogue changes need to be
version controlled, priceplans need to be attached to specific catalogue versions, and then priceplans need to be timestamped (introduction and release),
and then orders need a link to those historical priceplans. The traditional behaviour of course, is that it is **BOTTOM-UP**, so historical data
management and its inherent complexity to such an extremity is not a consideration.

