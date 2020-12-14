package com.revature.models;

public class BankingAccount {
		
	    private int customerId;
	
	    private String mailing_address;
	    private boolean pendingTransaction;
	    private BankingStatus bankingStatus;
	    
	    
	    
	    
		public int getCustomerId() {
			return customerId;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

	    public String getMailing_address() {
			return mailing_address;
		}
		public void setMailing_address(String mailing_address) {
			this.mailing_address = mailing_address;
		}
		public boolean isPendingTransaction() {
			return pendingTransaction;
		}
		public void setPendingTransaction(boolean pendingTransaction) {
			this.pendingTransaction = pendingTransaction;
		}
		public BankingStatus getBankingStatus() {
			return bankingStatus;
		}
		public void setBankingStatus(BankingStatus bankingStatus) {
			this.bankingStatus = bankingStatus;
		}
		
		
	  
	  
}
