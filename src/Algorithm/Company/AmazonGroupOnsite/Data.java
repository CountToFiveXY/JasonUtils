package Algorithm.Company.AmazonGroupOnsite;

import java.util.*;

public class Data {

	public List<ProductInventory> productInventories;
	public List<ShippingCost> shippingCosts;
	public List<Order> orders;
	
	public Data() {
		productInventories = new LinkedList<ProductInventory>();
		productInventories.add(new ProductInventory(1, 7, Region.north));
		productInventories.add(new ProductInventory(3, 70, Region.north));
		productInventories.add(new ProductInventory(3, 20, Region.north));
		productInventories.add(new ProductInventory(3, 40, Region.east));
		productInventories.add(new ProductInventory(3, 30, Region.north));
		
		shippingCosts = new LinkedList<ShippingCost>();
		shippingCosts.add(new ShippingCost(Region.north, Region.west, Method.express, 3, 10));
		shippingCosts.add(new ShippingCost(Region.north, Region.west, Method.ground, 1, 15));
		shippingCosts.add(new ShippingCost(Region.north, Region.east, Method.ground, 2, 20));
		shippingCosts.add(new ShippingCost(Region.north, Region.center, Method.express, 2, 5));
	
		orders = new LinkedList<Order>();
		orders.add(new Order(1, 6, Region.center, 4, 0.3));
		orders.add(new Order(1, 3, Region.west, 2, 0.0));
		orders.add(new Order(1, 4, Region.east, 0, 0.2));
		orders.add(new Order(3, 100, Region.center, 0, 0.1));
	}
	
}
