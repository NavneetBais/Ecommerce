import java.util.Date;
 
//The order will have the details such as the Product, shippedDate, orderDate, userId, Address, phoneNumber, paymentMode. 
public class Order  {
	private Products product ; 
	private String shippedDate ;
	private String orderDate; 
	private String userId ; 
	private String address ; 
	private String phoneNumber ; 
	private String paymentMode ;
	private int orderQuantity ;
	
	
	
	public Order(Products product, String shippedDate, String orderDate, String userId, String address, String phoneNumber,String paymentMode, int orderQuantity) {
		super();
		this.product = product;
		this.shippedDate = shippedDate;
		this.orderDate = orderDate;
		this.userId = userId;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.paymentMode = paymentMode;
		this.orderQuantity = orderQuantity ;
	}
	
	
	public Products getProduct() {
		return product;
	}
	
	public String getShippedDate() {
		return shippedDate;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getPaymentMode() {
		return paymentMode;
	}


	public int getOrderQuantity() {
		return orderQuantity;
	}


	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}


	@Override
	public String toString() {
		return " +++++++++++ Order Details ++++++++++++"
				+ "\n" + "Shipping Date=" + shippedDate + "\n" + "Order Date :"  + orderDate + "\n" + "Address :" + address + "\n"
				+ "Phone Number :" + phoneNumber + "\n" + "Payment Mode :" + paymentMode;
	}



	

	
	
	


}
