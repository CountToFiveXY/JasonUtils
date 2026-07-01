package Algorithm.Company.AmazonGroupOnsite;

public class ProductInventory {
	private int productId;
	private int quantity;
	private Region region;
	
	public ProductInventory(int productId, int quantity, Region region) {
		this.productId = productId;
		this.quantity = quantity;
		this.region = region;
	}
	
	public ProductInventory(ProductInventory other) {
		this.productId = other.productId;
		this.quantity = other.quantity;
		this.region = other.region;
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
}
