package Algorithm.Company.AmazonGroupOnsite;

import java.util.*;

public class ShippingCostExplorer {
	
	public List<ShippingCost> getShippingCosts(Region region) {
		Data data = new Data();
		List<ShippingCost> listOfShippingCosts = new LinkedList<>();
		for(ShippingCost shippingCost : data.shippingCosts) {
			if(shippingCost.getShipTo().equals(region)) {
				listOfShippingCosts.add(shippingCost);
			}
		}
		return listOfShippingCosts;
	}
}
