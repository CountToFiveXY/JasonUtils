package Algorithm.Company.AmazonGroupOnsite;

public class Order {

	private int productId;
	private int quantity;
	private Region region;
	private int expectedDays;
	private double date;
	
	public Order(int productId, int quantity, Region region, int expectedDays, double date) {
		this.productId = productId;
		this.quantity = quantity;
		this.region = region;
		this.expectedDays = expectedDays;
		this.date = date;
	}
	
	public Order(Order other) {
		this.productId = other.productId;
		this.quantity = other.quantity;
		this.region = other.region;
		this.expectedDays = other.expectedDays;
		this.date = other.date;
	}
	
	public int getProductId() {
		return this.productId;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public Region getRegion() {
		return this.region;
	}
	
	public int getExpectedDays() {
		return this.expectedDays;
	}
	
	public double getDate() {
		return this.date;
	}
}
