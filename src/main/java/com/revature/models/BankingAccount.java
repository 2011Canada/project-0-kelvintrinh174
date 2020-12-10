package com.revature.models;

public abstract class BankingAccount {
		
	  private int customerId;
	  
	  abstract void view();
	  abstract boolean deposit(double amount);
	  
	  
		public int getCustomerId() {
			return customerId;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
	  
	  
}
