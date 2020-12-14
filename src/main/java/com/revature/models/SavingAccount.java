package com.revature.models;

public class SavingAccount extends BankingAccount implements BankingActivity{
	
	private int accountNumber;
	private double balance;
	
	
	public SavingAccount(int accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void view() {
		// TODO Auto-generated method stub
		
	}

	public boolean deposit(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean withdraw(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
