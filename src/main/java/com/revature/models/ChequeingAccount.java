package com.revature.models;

public class ChequeingAccount extends BankingAccount {
	
	private int accountNumber;
	private double balance;
	
	
	public ChequeingAccount(int accountNumber, double balance) {
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

	@Override
	void view() {
		// TODO Auto-generated method stub

	}

	@Override
	boolean deposit(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
