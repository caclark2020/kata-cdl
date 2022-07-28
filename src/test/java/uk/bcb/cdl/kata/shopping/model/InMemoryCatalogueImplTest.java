package uk.bcb.cdl.kata.shopping.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * <p>Main test class for Catalogue object.</p>
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 *
 */
public class InMemoryCatalogueImplTest {

	@Test
	void testConstructorAndContains() 
	{
		InMemoryCatalogueImpl catalogue = new InMemoryCatalogueImpl ("1","2","3","4");
		assertTrue(catalogue.contains("1"), "Expected catalogue to contain SKU '1'");
		assertTrue(catalogue.contains("2"), "Expected catalogue to contain SKU '2'");
		assertTrue(catalogue.contains("3"), "Expected catalogue to contain SKU '3'");
		assertTrue(catalogue.contains("4"), "Expected catalogue to contain SKU '4'");
	}

	@Test
	void testConstructorAndGetAll() 
	{
		InMemoryCatalogueImpl catalogue = new InMemoryCatalogueImpl ("1","2","3","4");
		String [] array = catalogue.getAll();
		
		assertTrue(array[0].equals("1"), "Expected array entry 0 to be '1'");
		assertTrue(array[1].equals("2"), "Expected array entry 1 to be '2'");
		assertTrue(array[2].equals("3"), "Expected array entry 2 to be '3'");
		assertTrue(array[3].equals("4"), "Expected array entry 3 to be '4'");
	}
	
	@Test
	void testDuplicateAdd() 
	{
		InMemoryCatalogueImpl catalogue = new InMemoryCatalogueImpl ();
		catalogue.add("FOO");
		try 
		{
			catalogue.add("FOO");
		} 
		catch (IllegalArgumentException e) 
		{
			// OK
			return;
		}
		
		fail("Expected an IllegalArgumentException when adding the same SKU twice");
	}
	
	@Test
	void testAddAndContains() 
	{
		InMemoryCatalogueImpl catalogue = new InMemoryCatalogueImpl ();
		catalogue.add("DUCK");
		if (!catalogue.contains("DUCK"))
			fail ("Expected the catalogue to contain SKU 'DUCK'");
	}
	
	@Test
	void testAddAndGetAll() 
	{
		InMemoryCatalogueImpl catalogue = new InMemoryCatalogueImpl ();
		catalogue.add("CAT");
		catalogue.add("DOG");
		String [] array = catalogue.getAll();
		
		assertTrue(array[0].equals("CAT"), "Expected array entry 0 to be 'CAT'");
		assertTrue(array[1].equals("DOG"), "Expected array entry 1 to be 'DOG'");
		
	}
}
