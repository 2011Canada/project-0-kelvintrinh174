package com.revature.services;

import com.revature.repositories.UserImplementationDAO;

public class EmployeeServiceImplementation implements EmployeeService, UserService {
	
	
	private UserImplementationDAO uid;
	
	public EmployeeServiceImplementation(UserImplementationDAO uid) {
		   this.uid = uid;
	}
	
	//
	public void userLogIn(String email, String password, boolean isCustomer) {
		// TODO Auto-generated method stub

	}

	public boolean setCustomerStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public void viewCustomerAccount() {
		// TODO Auto-generated method stub

	}

}
