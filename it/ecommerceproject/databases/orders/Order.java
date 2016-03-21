package it.ecommerceproject.databases.orders;

import it.ecommerceproject.util.Identificable;

public class Order extends Identificable{

	
	private String user;
	private int productID;
	
	
	protected Order(int id,String user, int productID){
		setID(id);
		this.user = user;
		this.productID = productID;
	}
	
	
	public Order(String user,int productID){
		this.user = user;
		this.productID = productID;
		setID(-1);
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public int getProductID() {
		return productID;
	}


	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	
	

}
