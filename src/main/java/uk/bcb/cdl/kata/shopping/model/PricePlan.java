package uk.bcb.cdl.kata.shopping.model;

/**
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 */

public interface PricePlan {
	/**
	 * <p>An offer is a reduced price for a bulk purchase of SKUs (outside of the basic price)</p>
	 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
	 */
	public static record Offer (String sku, int amount, int pence)
	{
		public final String toPrettyString ()
		{
			return new String (sku + ":  "+amount+" for "+pence +"p");
		}
	}

	/**
	 * <p>A base price is the basic price for an SKU (outside of offers)</p>
	 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
	 */
	public static record BasePrice (String sku, int pence)
	{
		public final String toPrettyString ()
		{
			return new String (sku + ":  "+pence+"p");
		}
	}
	
	public BasePrice getBasePrice (String sku);
	public Offer getOffer (String sku);
	
	public BasePrice [] getAllBasePrices ();
	public Offer [] getAllOffers ();
}
