package it.ecommerceproject.databases.products;

import it.ecommerceproject.util.Identificable;

public class Product extends Identificable{

	
	private String name;
	private String description;
	private double price;
	
	
	public Product(String name,String description,double price){
		this.name = name;
		this.description = description;
		this.price = price;
		setID(-1);
	}
	
	protected Product(int id,String name,String description,double price){
		setID(id);
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
}
