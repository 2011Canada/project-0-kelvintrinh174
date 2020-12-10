package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserImplementationDAO;

public class CustomerServiceImplementation implements CustomerService,UserService {
    
    private UserImplementationDAO uid;
	    
	public CustomerServiceImplementation(UserImplementationDAO uid) {
		super();
		this.uid = uid;
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
	public void userLogIn(String email, String password, boolean isCustomer) {
		User user = uid.findOne(email, password, isCustomer);	
		if(user!= null) {
		   System.out.println("Welcome, "+ user.getEmail());
		}
		else {
			System.out.println(" User is not found");
		}
	}
   
}
