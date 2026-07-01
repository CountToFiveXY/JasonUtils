package Algorithm.Company.AmazonGroupOnsite;

import java.util.*;

public class Solution {
	
	private List<Region> regionsList; 
	private Map<String, ShippingCost> fastestShippingDaysMap;
	
	public Solution() {
		regionsList = new ArrayList<Region>();
		buildRegionList();
		fastestShippingDaysMap = new HashMap<String, ShippingCost>();
		buildFastestShippingDaysMap();
	}
	
	public void buildRegionList() {
		regionsList.add(Region.east);
		regionsList.add(Region.south);
		regionsList.add(Region.west);
		regionsList.add(Region.north);
		regionsList.add(Region.center);
	}
	
	public void buildFastestShippingDaysMap() {
		ShippingCostExplorer shippingCostExplorer = new ShippingCostExplorer();
		for(int i = 0; i < regionsList.size(); i++) {
			Region destinatonRegion = regionsList.get(i);
			List<ShippingCost> shippingCostsList = shippingCostExplorer.getShippingCosts(destinatonRegion);
			for(ShippingCost shippingCost : shippingCostsList) {
				String key = shippingCost.getShipFrom().toString() + shippingCost.getShipTo().toString();
				if(!fastestShippingDaysMap.containsKey(key)) {
					fastestShippingDaysMap.put(key, shippingCost);
				} else {
					if(fastestShippingDaysMap.get(key).getDays() > shippingCost.getDays()) {
						fastestShippingDaysMap.put(key, shippingCost);
					}
				}
			}
		}
	}
	
	public List<ProductShippingCost> milestone1(int productId, Region destination) {
		List<ProductShippingCost> listOfProductShippingCosts = new LinkedList<ProductShippingCost>();
		ShippingCostExplorer shippingCostExplorer = new ShippingCostExplorer();
		List<ShippingCost> listOfShippingCosts = shippingCostExplorer.getShippingCosts(destination);
		Map<Region, List<ShippingCost>> shippingCostMap = new HashMap<Region, List<ShippingCost>>();
		for(ShippingCost shippingCost : listOfShippingCosts) {
			if(!shippingCostMap.containsKey(shippingCost.getShipFrom())) {
				List<ShippingCost> shippingCostsList = new LinkedList<ShippingCost>();
				shippingCostsList.add(shippingCost);
				shippingCostMap.put(shippingCost.getShipFrom(), shippingCostsList);
			} else {
				shippingCostMap.get(shippingCost.getShipFrom()).add(shippingCost);
			}
		}
		ProductInventoryExplorer productInventoryExplorer = new ProductInventoryExplorer();
		List<ProductInventory> listOfProductInventories = productInventoryExplorer.getProductInventory(productId);
		for(ProductInventory productInventory : listOfProductInventories) {
			List<ShippingCost> shippingCosts = shippingCostMap.get(productInventory.getRegion());
			ProductShippingCost productShippingCost = new ProductShippingCost();
			if(shippingCosts != null && shippingCosts.size() != 0) {
				productShippingCost.setProductInventory(productInventory);
				productShippingCost.setListOfShippingCost(listOfShippingCosts);
				listOfProductShippingCosts.add(productShippingCost);
			}
		}
		return listOfProductShippingCosts;
	}
	
	public void milestone2(List<Order> orders) {
		// sort the order by quantity so less quantity order can be shipped earlier

		orders.sort((a, b) -> a.getQuantity() - b.getQuantity());
		
		for(Order order : orders) {
			List<ProductShippingCost> productShippingCostList = milestone1(order.getProductId(), order.getRegion()); 
			Collections.sort(productShippingCostList, new Comparator<ProductShippingCost>() {
				@Override
				public int compare(ProductShippingCost p1, ProductShippingCost p2) {
					return fastestShippingDaysMap.get(
							p1.getProductInventory().toString() + 
							order.getRegion().toString()).getDays()
							-
							fastestShippingDaysMap.get(
							p2.getProductInventory().toString() + 
							order.getRegion().toString()).getDays();
				}
			});
			for(ProductShippingCost productShippingCost : productShippingCostList) {
				// ship
			}
		}
	}
}


class ProductShippingCost {
	private ProductInventory inventory;
	private List<ShippingCost> listOfShippingCosts;
	
	public ProductShippingCost() {
	}
	
	public void setProductInventory(ProductInventory inventory) {
		this.inventory = inventory;
	}
	
	public void setListOfShippingCost(List<ShippingCost> listOfShippingCosts) {
		this.listOfShippingCosts = listOfShippingCosts;
	}
	
	public ProductInventory getProductInventory() {
		return this.inventory;
	}
	
	public List<ShippingCost> getShippingCostList() {
		return this.listOfShippingCosts;
	}
	
}