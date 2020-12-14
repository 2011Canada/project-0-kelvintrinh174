package com.revature.menus;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;
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
	
	//1
	public void manageUserAccountInput() throws UserNotFoundException, InternalErrorException, SQLException,InputMismatchException {
 
		String email,password;	
		int accountType;
		
		System.out.println("Your banking options:\n 1. New customer \n 2. Existing customer  \n 3. Employee \n");
	    System.out.println("Please enter to start!");
		this.userIn.nextLine();
		System.out.println("Your choice is: ");
	    try {
	    	
	    	accountType = this.userIn.nextInt();
	    	if(accountType < 0 || accountType > 3) {
	    		System.out.println("Please select the account 1, 2, or 3");
	    	}
	    	
	    	else {
	    		//this.userIn.nextLine();
	    		//System.out.println("You're awesome!\n");

	    		if(accountType == 1) {
	    			this.userIn.nextLine();
	    			manageNewCustomer();
	    		} if(accountType == 2) {
	    			
	    			System.out.println("Please enter your email: ");
	    			email = this.userIn.nextLine();
	    			System.out.println("Please enter your password: ");
	    			password = this.userIn.nextLine();
	    			csi.userLogIn(email,password, true);
	    		}
	    		else if(accountType == 3){
	    			System.out.println("Please enter your email: ");
	    			email = this.userIn.nextLine();
	    			System.out.println("Please enter your password: ");
	    			password = this.userIn.nextLine();
	    			esi.userLogIn(email,password, false);
	    		}
	    			    			    		
	    	}
	    	
	    } catch(InputMismatchException e) {
	    	 System.out.println("Please select the account based on number");
	    } catch(UserNotFoundException u) {
	    	System.out.println("User is not found");
	    }
	}
    
	public void manageNewCustomer() {
		String email,password,firstName,lastName;	
		System.out.println("Choose the best options for you: \n");
		System.out.println("1. Register new account without initial deposit\n"
				+ "2. Register new account with deposit \n");
		System.out.println("Your choice is: ");
		int option = this.userIn.nextInt();
		User customer = new Customer();
		
		this.userIn.nextLine();
		System.out.println("Please enter your email: ");
		email = this.userIn.nextLine();
		customer.setEmail(email);
		System.out.println("Please enter your password: ");
		password = this.userIn.nextLine();
		customer.setPassword(password);
		System.out.println("Please enter your first name: ");
		firstName = this.userIn.nextLine();
		customer.setFirstName(firstName);
		System.out.println("Please enter your last name: ");
		lastName = this.userIn.nextLine();
		customer.setLastName(lastName);
		//System.out.println(customer);
		if(option == 1) {
			
			
			this.csi.applyNewAccountWithBalance(customer, 0);

		}
		else if (option == 2) {
			System.out.println("Please enter your first deposit: ");
			double balance = this.userIn.nextDouble();
			this.csi.applyNewAccountWithBalance(customer, balance);
		} else {
			
		}
		
		System.out.println("Please press enter to come to the main menu!");
		this.userIn.nextLine();
	}
	 
	  
	  
	  
	  
	  
	  
	  
}
