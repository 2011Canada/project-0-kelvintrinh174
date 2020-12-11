package com.revature.menus;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.services.CustomerServiceImplementation;
import com.revature.services.EmployeeServiceImplementation;

public class UserMenu implements Displayable {
		
	  private CustomerServiceImplementation csi;
	  private EmployeeServiceImplementation esi;
	  Scanner userIn;
	 
	  
	  public UserMenu(CustomerServiceImplementation csi, EmployeeServiceImplementation esi) {
		  this.csi = csi;
		  this.esi = esi;
		  userIn = new Scanner(System.in);
	  }
	  

	public String display() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void manageUserAccountInput() throws UserNotFoundException, InternalErrorException, SQLException {
 
		String email,password;	
		System.out.println("Select your type of account:\n 1. Customer \n 2. Employee");

	    int accountType = userIn.nextInt();
	    try {
	    	
	    	if(accountType < 0 || accountType > 2) {
	    		System.out.println("Please select the account 1 or 2");
	    	}
	    	
	    	else {
	    		this.userIn.nextLine();
	    		System.out.println("You're awesome!\n");
    			System.out.println("Please enter your email: ");
    			email = userIn.nextLine();
    			System.out.println("Please enter your password: ");
    			password = userIn.nextLine();
	    		if(accountType == 1) {
	    			csi.userLogIn(email,password, true);
	    		} //accountType == 2
	    		else {
	    			esi.userLogIn(email,password, false);
	    		}
	    		//userIn.close();
	    		//inputEmail.close();
	    		//inputPassword.close();
	    		
	    		
	    	}
	    	
	    } catch(InputMismatchException e) {
	    	 System.out.println("Please select the account based on number");
	    }
	}
    
	
	 
	  
	  
	  
	  
	  
	  
	  
}
