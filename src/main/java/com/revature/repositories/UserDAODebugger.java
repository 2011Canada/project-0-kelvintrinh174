package com.revature.repositories;

import java.sql.SQLException;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;

public class UserDAODebugger {

	public static void main(String[] args) throws UserNotFoundException, SQLException {
		// TODO Auto-generated method stub
		UserDAO ud = new UserPostgresDAO();
		User customer = new Customer();
		customer.setEmail("nick@gmail.com");
	    try {
	    	ud.findOne("nick@gmail.com", "12345", true);
			System.out.println(ud.findCustomerInfoByEmail(customer));
		} catch (InternalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//User customer = new Customer("Bob@gmail.com","12345","Bob","Frac");
		//System.out.println(ud.createCustomerAccount(customer,120));

	}

}
