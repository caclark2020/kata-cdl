package uk.bcb.cdl.kata.shopping.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 */
public class InMemoryPricePlanImpl 
	implements PricePlan
{
	private Map<String, BasePrice> basePrices = new HashMap<>();
	private Map<String, Offer> offers = new HashMap<>();
	
	public InMemoryPricePlanImpl() {
	}

	@Override
	public BasePrice getBasePrice(String sku) 
	{
		return basePrices.get(sku);
	}

	@Override
	public Offer getOffer(String sku) 
	{
		return offers.get(sku);
	}

	public final void add (Offer offer)
	{
		this.offers.put(offer.sku(), offer);
	}

	public final void add (BasePrice price)
	{
		this.basePrices.put(price.sku(), price);
	}
	
	@Override
	public BasePrice[] getAllBasePrices() {
		return basePrices.values().toArray(new BasePrice[0]);
	}
	
	@Override
	public Offer[] getAllOffers() {
		return offers.values().toArray(new Offer[0]);
	}
}
