package com.revature.models;

public class ChequeingAccount extends BankingAccount {
	
	private String accountNumber;
	private double balance;
	
	
	public ChequeingAccount(String accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public ChequeingAccount() {
		
	}
	
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String string) {
		this.accountNumber = string;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance += balance;
	}
	
	public void withDraw(double amount) {
		this.balance -= amount;
	}


	@Override
	public String toString() {
		return "ChequeingAccount [accountNumber=" + accountNumber + ", balance=" + balance + "]\n";
	}

    
	
	
	
}
