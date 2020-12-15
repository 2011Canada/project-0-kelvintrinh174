package com.revature.services;

import java.sql.SQLException;
import java.util.List;

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
	public User userLogIn(String email, String password, boolean isCustomer) throws UserNotFoundException, InternalErrorException, SQLException {
		User user = upd.findOne(email, password, isCustomer);	
		if(user!= null) {
		   System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
		   return user;
		}
		else {
			System.out.println(" User is not found");
		}
		return null;

	}

	public boolean setCustomerStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public void viewCustomerAccount(User customer) {
		// TODO Auto-generated method stub

	}

	public List<User> viewListPendingUser() throws InternalErrorException, SQLException {
		List<User> list = upd.findPendingCustomer();
		if(list.size() != 0) {
			return list;
		}
		
		return null;
	}


    //Employee can view customer account using their email
	public List<Object> viewCustomerInfo(User customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
