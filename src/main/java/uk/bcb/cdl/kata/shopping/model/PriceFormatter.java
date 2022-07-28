package uk.bcb.cdl.kata.shopping.model;

import java.text.DecimalFormat;

/**
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 */

public class PriceFormatter 
{
	private static final DecimalFormat PRICE_FORMAT = new DecimalFormat ("\u00A3 #####0.00");
	
	public static final String formatPence (int pence)
	{
		double value = ((double)pence)/100.0;
		return PRICE_FORMAT.format(value);
	}

	public static final String formatPrice (double price)
	{
		return PRICE_FORMAT.format(price);
	}
}
