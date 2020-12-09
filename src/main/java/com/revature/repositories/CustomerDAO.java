package com.revature.repositories;

import com.revature.models.User;

public interface CustomerDAO {
		
	public void viewBalance();
	
	public void applyNewAccount(User customer);
	public void applyNewAccountWithBalance(User customer, double balance);
	
	public boolean transferMoney(String email, double amount);
	public boolean acceptMoneyTransfer();
	
	
}
