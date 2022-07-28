package uk.bcb.cdl.kata.shopping.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * <p>Main test class for PriceFormatter object.</p>
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 */
public class PriceFormatterTest 
{
	@Test
	public final void testPriceFormatterPence1 ()
	{
		String price = PriceFormatter.formatPence(99);
		char [] chars = price.toCharArray();
		
		assertEquals (6, chars.length, "Expected price string to be 6 characters in length.");
		assertEquals('\u00A3', chars[0], "Expected first symbol to be a pound symbol '\u00A3'");
		assertEquals(' ', chars[1], "Expected second symbol to be a space.");
		assertEquals('0', chars[2], "Expected third symbol to be a 0 digit.");
		assertEquals('.', chars[3], "Expected fourth symbol to be a dot");
		assertEquals('9', chars[4], "Expected fifth symbol to be a 9 digit.");
		assertEquals('9', chars[5], "Expected sixth symbol to be a 9 digit.");
	}

	@Test
	public final void testPriceFormatterPence2 ()
	{
		String price = PriceFormatter.formatPence(1250);
		char [] chars = price.toCharArray();
		
		assertEquals (7, chars.length, "Expected price string to be 7 characters in length.");
		assertEquals('\u00A3', chars[0], "Expected first symbol to be a pound symbol '\u00A3'");
		assertEquals(' ', chars[1], "Expected second symbol to be a space.");
		assertEquals('1', chars[2], "Expected third symbol to be a 1 digit.");
		assertEquals('2', chars[3], "Expected fourth symbol to be a 2 digit.");
		assertEquals('.', chars[4], "Expected fifth symbol to be a dot");
		assertEquals('5', chars[5], "Expected sixth symbol to be a 5 digit.");
		assertEquals('0', chars[6], "Expected seventh symbol to be a 0 digit.");
	}

}
