package com.revature.models;

public interface BankingActivity {
	void view();
	boolean deposit(double amount);
	boolean withdraw(double amount);
	
}
