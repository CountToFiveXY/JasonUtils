package Algorithm.Company.AmazonGroupOnsite;

public class ShippingCost {
	
	private Region shipFrom;
	private Region shipTo;
	private Method method;
	private int costPerItem;
	private int days;
	
	public ShippingCost(Region shipFrom, Region shipTo, Method method, int costPerItem, int days) {
		this.shipFrom = shipFrom;
		this.shipTo = shipTo;
		this.method = method;
		this.costPerItem = costPerItem;
		this.days = days;
	}
	
	public Region getShipFrom() {
		return this.shipFrom;
	}
	
	public Region getShipTo() {
		return this.shipTo;
	}
	
	public Method getMethod() {
		return this.method;
	}
	
	public int getCostPerItem() {
		return this.costPerItem;
	}
	
	public int getDays() {
		return this.days;
	}
	
}
