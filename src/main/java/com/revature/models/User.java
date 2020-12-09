package com.revature.models;

public abstract class User {
      private int userId;
      private String email;
      private String password;
      private boolean isCustomer;
      
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}
	
	    
}
