package com.revature.services;

import java.sql.SQLException;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
//import com.revature.repositories.UserImplementationDAO;
import com.revature.repositories.UserPostgresDAO;

public class EmployeeServiceImplementation implements EmployeeService, UserService {
	
	
	//private UserImplementationDAO uid;
	private UserPostgresDAO upd;
	
	public EmployeeServiceImplementation(UserPostgresDAO upd) {
		   this.upd = upd;
	}
	
	//
	public void userLogIn(String email, String password, boolean isCustomer) throws UserNotFoundException, InternalErrorException, SQLException {
		User user = upd.findOne(email, password, isCustomer);	
		if(user!= null) {
		   System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
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
