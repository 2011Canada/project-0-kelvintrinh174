package com.revature.repositories;

import java.sql.SQLException;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.ChequeingAccount;
import com.revature.models.Customer;
import com.revature.models.SavingAccount;
import com.revature.models.User;

public class UserDAODebugger {

	public static void main(String[] args) throws UserNotFoundException, SQLException {
		// TODO Auto-generated method stub
		UserDAO ud = new UserPostgresDAO();
		User customer = new Customer();
		customer.setUserId(5);
		//customer.setInitialDeposit(174);
		  ChequeingAccount newChequingAccount = new ChequeingAccount();
		  SavingAccount	newSavingAccount = new SavingAccount();
		  newChequingAccount.setAccountNumber(generateAccountNumber());
		  newChequingAccount.setBalance(customer.getInitialDeposit());
		  newSavingAccount.setAccountNumber(generateAccountNumber());
		  	  
		  //ud.acceptOne(customer,newChequingAccount,newSavingAccount);
		  System.out.println(ud.rejectOne(customer));
		//User customer = new Customer("Bob@gmail.com","12345","Bob","Frac");
		//System.out.println(ud.createCustomerAccount(customer,120));

	}
	
	public static String generateAccountNumber() {
		int i =1;
		StringBuffer accountNumber = new StringBuffer("");
		while (true) {
			if(i==7) {
				break;
			}		
			int a = (int) (Math.random() * 10);
			
			accountNumber.append(a);
			i++;
		}
		return accountNumber.toString();
	}

}
