package com.revature.repositories;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.ChequeingAccount;
import com.revature.models.SavingAccount;
import com.revature.models.User;

public interface UserDAO {
       
	   
	   public User createCustomerAccount(User user, double balance);
	   public User findOne(String email, String password, boolean isCustomer) 
			   throws UserNotFoundException, InternalErrorException, SQLException;
       
	   public List<User> findPendingCustomer()
	   throws InternalErrorException, SQLException;
	   public List<User> findAll();
       public boolean acceptOne(User user,ChequeingAccount ca,SavingAccount sa);
       public boolean rejectOne(User user); 
       List<Object> findCustomerInfoByEmail(User user) throws InternalErrorException;
       
}
