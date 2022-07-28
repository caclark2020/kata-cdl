package uk.bcb.cdl.kata.shopping.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 */
public class InMemoryCatalogueImpl implements Catalogue {

	private final List<String> skus = new ArrayList<>();
	
	public InMemoryCatalogueImpl(String... skus) {
		this.skus.addAll(Arrays.asList(skus));
	}

	public void add(String sku) {
		if (skus.contains(sku))
			throw new IllegalArgumentException("SKU '"+sku+"' already exists in this catalogue.");
		skus.add(sku);
	}

	@Override
	public boolean contains(String sku) {
		return skus.contains(sku);
	}

	@Override
	public String[] getAll() {
		return skus.toArray(new String[0]);
	}
}
