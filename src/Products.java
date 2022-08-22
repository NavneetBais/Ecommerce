//lass with the productId, productName, quantity, and unitPrice.
public class Products {
	int productID ; 
	String productName ;
	int quantity ; 
	int unitPrice ;
	
	public Products(int productID, String productName, int quantity, int unitPrice) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public int getProductID() {
		return productID;
	}

	public String getProductName() {
		return productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	
	
	
	
	
}


