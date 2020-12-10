package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserImplementationDAO;

public class EmployeeServiceImplementation implements EmployeeService, UserService {
	
	
	private UserImplementationDAO uid;
	
	public EmployeeServiceImplementation(UserImplementationDAO uid) {
		   this.uid = uid;
	}
	
	//
	public void userLogIn(String email, String password, boolean isCustomer) {
		User user = uid.findOne(email, password, isCustomer);	
		if(user!= null) {
		   System.out.println("Welcome, "+ user.getEmail());
		}
		else {
			System.out.println(" User is not found");
		}

	}

	public boolean setCustomerStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public void viewCustomerAccount() {
		// TODO Auto-generated method stub

	}

}
