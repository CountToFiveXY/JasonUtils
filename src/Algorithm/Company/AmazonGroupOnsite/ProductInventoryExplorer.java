package Algorithm.Company.AmazonGroupOnsite;

import java.util.*;

public class ProductInventoryExplorer {

	public List<ProductInventory> getProductInventory(int productId) {
		Data data = new Data();
		List<ProductInventory> listOfProductInventories = new LinkedList<ProductInventory>();
		for(ProductInventory productInventory : data.productInventories) {
			if(productInventory.getProductId() == (productId)) {
				listOfProductInventories.add(productInventory);
			}
		}
		return listOfProductInventories;
	}
}
