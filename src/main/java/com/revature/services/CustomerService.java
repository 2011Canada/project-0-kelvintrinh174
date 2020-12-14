package com.revature.services;

import com.revature.models.User;

public interface CustomerService {
	  
	public void viewBalance();
	
	public void applyNewAccountWithBalance(User customer, double balance);
	
	public boolean transferMoney(String email, double amount);
	public boolean acceptMoneyTransfer();
}
