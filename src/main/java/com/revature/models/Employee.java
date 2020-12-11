package com.revature.models;

public class Employee extends User {

	public Employee(String email,String password) {
		super(email, password);
		super.setCustomer(false);
	}
	
	public Employee() {
		super.setCustomer(false);
	}

}
