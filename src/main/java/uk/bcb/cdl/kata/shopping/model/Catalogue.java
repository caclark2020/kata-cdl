package uk.bcb.cdl.kata.shopping.model;

/**
 * 
 * <ul>
 * <li>A Catalogue has no database/file backing for this kata, as there is no requirement for it. However, as an interface,
 * storage can be abstracted away in the implementation class.</li>
 * <li>A Catalogue is quite simple according to the requirements of the kata, as there is only a requirement of being able
 * to consider an SKU as an individual identifiable item by string, and additional data is not in scope.</li>
 * <li>A decision was made to make SKUs <b>case sensitive</b> for now, though that was not clarified in the original
 * requirements.</li>
 * </ul>
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 *
 */
public interface Catalogue 
{
	/**
	 * <p>Checks if an SKU (Shop Keeping Unit) is an identifiable item in the catalogue.</p>
	 * @param sku The identifier of the purchasable item - known as an SKU.
	 * @return A flag indicating if the SKU is known in the catalogue (true) or not (false).
	 */
	public boolean contains (String sku);

	/**
	 * <p>Get all SKUs in the catalogue.</p>
	 * @return An array of SKUs as strings.
	 */
	public String [] getAll ();
}
