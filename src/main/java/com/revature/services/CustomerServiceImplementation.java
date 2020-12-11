package com.revature.services;

import java.sql.SQLException;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User; 
import com.revature.repositories.UserPostgresDAO;

public class CustomerServiceImplementation implements CustomerService,UserService {
    
    //private UserImplementationDAO uid;
    private UserPostgresDAO upd;
	    
	public CustomerServiceImplementation(UserPostgresDAO upd) {
		super();
		this.upd = upd;
	}

	public void viewBalance() {
		// TODO Auto-generated method stub

	}

	public void applyNewAccount(User customer) {
		// TODO Auto-generated method stub

	}

	public void applyNewAccountWithBalance(User customer, double balance) {
		// TODO Auto-generated method stub

	}

	public boolean transferMoney(String email, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean acceptMoneyTransfer() {
		// TODO Auto-generated method stub
		return false;
	}
    
	//Customer Login
	public void userLogIn(String email, String password, boolean isCustomer) throws UserNotFoundException, InternalErrorException, SQLException {
		User user = upd.findOne(email, password, isCustomer);	
		if(user!= null) {
		   System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
		}
		else {
			System.out.println("User is not found!");
		}
	}
   
}
