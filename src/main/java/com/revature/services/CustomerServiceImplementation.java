package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.BankingAccount;
import com.revature.models.ChequeingAccount;
import com.revature.models.SavingAccount;
import com.revature.models.User;
import com.revature.repositories.BankingAccountDAO;
import com.revature.repositories.UserPostgresDAO;

public class CustomerServiceImplementation implements CustomerService,UserService {
    
    //private UserImplementationDAO uid;
    private UserPostgresDAO upd;
    private BankingAccountDAO bad;
	    
	public CustomerServiceImplementation(UserPostgresDAO upd,BankingAccountDAO bad) {
		super();
		this.upd = upd;
		this.bad = bad;
	}

	public List<Object> viewCustomerInfo(User customer) {
		List<Object> listInfo;
		try {
			listInfo = upd.findCustomerInfoByEmail(customer);	
			if(listInfo.size() != 0) {
				return listInfo;
			}
		} catch (InternalErrorException e) {
			e.printStackTrace();
			System.out.println(e);
		} 

		return null;		
	}


	public void applyNewAccountWithBalance(User customer, double balance) {
		  User user = upd.createCustomerAccount(customer, balance);
		  if(user!= null) {
			   System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
			   System.out.println("Thank you for registering new account! Your account is reviewed!");
			}
			else {
				System.out.println("Creation is not successful!");
			}
		  

	}
	
	
	public boolean deposit(int bankId,BankingAccount existingAccount,double amount) {
		if(existingAccount instanceof ChequeingAccount) {
			((ChequeingAccount)existingAccount).setBalance(amount);
		} else if (existingAccount instanceof SavingAccount){
			((SavingAccount)existingAccount).setBalance(amount);
		}
		return bad.updateBalance(bankId ,existingAccount);
		
	}


	public boolean withdraw(int bankId,BankingAccount existingAccount,double amount) {
		if(existingAccount instanceof ChequeingAccount) {
			((ChequeingAccount)existingAccount).withDraw(amount);
		} else if (existingAccount instanceof SavingAccount){
			((SavingAccount)existingAccount).withDraw(amount);
		}
		return bad.updateBalance(bankId ,existingAccount);
	}
	
	
	public boolean transferMoney(String email, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean acceptMoneyTransfer() {
		// TODO Auto-generated method stub
		return false;
	}
    
	//Customer Login
	public User userLogIn(String email, String password, boolean isCustomer) throws UserNotFoundException, InternalErrorException, SQLException {
		User user = upd.findOne(email, password, isCustomer);	
		if(user!= null) {
		   //System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
		   return user;
		}

		return null;
	}
   
}
