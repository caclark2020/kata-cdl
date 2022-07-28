package uk.bcb.cdl.kata.shopping.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uk.bcb.cdl.kata.shopping.model.PricePlan.BasePrice;
import uk.bcb.cdl.kata.shopping.model.PricePlan.Offer;

/**
 * <p>Main test class for PricePlan object.</p>
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 *
 */
public class InMemoryPricePlanImplTest {

	@Test
	public final void testAddAndGetBasePrice ()
	{
		InMemoryPricePlanImpl priceplan = new InMemoryPricePlanImpl();
		priceplan.add(new BasePrice ("CAT", 2250));
		BasePrice baseprice = priceplan.getBasePrice("CAT");
		assertNotNull(baseprice, "BasePrice returned should not be null.");
		assertEquals("CAT", baseprice.sku(), "Expected SKU to be 'CAT'");
		assertEquals(2250, baseprice.pence(), "Expected pence to be 2250");
	}

	@Test
	public final void testAddAndGetOffer ()
	{
		InMemoryPricePlanImpl priceplan = new InMemoryPricePlanImpl();
		priceplan.add(new Offer("CAT", 4, 900));
		Offer offer = priceplan.getOffer("CAT");
		assertNotNull(offer, "Offer returned should not be null.");
		assertEquals("CAT", offer.sku(), "Expected SKU to be 'CAT'");
		assertEquals(4, offer.amount(), "Expected amount to be 4");
		assertEquals(900, offer.pence(), "Expected pence to be 900");
	}

}
