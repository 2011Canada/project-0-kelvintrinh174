package com.revature.repositories;

import com.revature.models.BankingAccount;
import com.revature.models.User;

public interface BankingAccountDAO {
		
	   public BankingAccount saveOne(BankingAccount newAccount);
	   public BankingAccount findOne(User userId);
	   public void updateBalance(BankingAccount existingAccount, double balance);
	   public void updateAccount(BankingAccount account);
	  
	   
}
