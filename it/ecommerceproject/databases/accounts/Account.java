package it.ecommerceproject.databases.accounts;

import it.ecommerceproject.util.Identificable;

public class Account extends Identificable{

	
	private String username;
	private String password;
	
	
	// solo il database puo inserire l'id
	protected Account(int id, String username, String password) {
		setID(id);
		this.username = username;
		this.password = password;
	}
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
		setID(-1);
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
