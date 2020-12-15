package com.revature.menus;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.BankingAccount;
import com.revature.models.ChequeingAccount;
import com.revature.models.Customer;
import com.revature.models.SavingAccount;
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
	
	//0
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
	    		if(accountType == 1) {
	    			this.userIn.nextLine();
	    			//1
	    			manageNewCustomer();
	    		} if(accountType == 2) {
	    			this.userIn.nextLine();
	    			System.out.println("Welcome back to our bank!\n ");
	    			System.out.println("Please enter your email: ");
	    			email = this.userIn.nextLine();
	    			System.out.println("Please enter your password: ");
	    			password = this.userIn.nextLine();
	    			User customer = csi.userLogIn(email,password, true);
	    			if(customer.isCustomer()) {
	    				//Enter customer menu
	    				manageCustomerAccount(customer);
	    			};
	    		}
	    		else if(accountType == 3){
	    			System.out.println("Welcome back to our bank!\n ");
	    			System.out.println("Please enter your employee email: ");
	    			email = this.userIn.nextLine();
	    			System.out.println("Please enter your password: ");
	    			password = this.userIn.nextLine();
	    			esi.userLogIn(email,password, false);
	    		}
	    			    			    		
	    	}
	    	
	    } catch(InputMismatchException e) {
	    	 System.out.println("Please select the options based on number!\n");
	    } catch(UserNotFoundException u) {
	    	System.out.println("Wrong user or password! \n");
	    }
	}
    //1
	public void manageNewCustomer() {
	
		while(true) {
			String email,password,firstName,lastName;	
			System.out.println("Choose the best options for you: \n");
			System.out.println("1. Register new account without initial deposit\n"
					+ "2. Register new account with deposit \n"
					+ "3. Return to main menu");
			System.out.println("Your choice is: ");
			int option = this.userIn.nextInt();
			if(option ==3)
				break;
			User customer = new Customer();
			if(option == 1 || option  == 2) {
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
			} 
			
			if(option == 1) {
				
				this.csi.applyNewAccountWithBalance(customer, 0);

			}
			else if (option == 2) {
				System.out.println("Please enter your first deposit: ");
				double balance = this.userIn.nextDouble();
				this.csi.applyNewAccountWithBalance(customer, balance);
			} else {
				this.userIn.nextLine();
				System.out.println("Please enter option 1 or 2! \n");
				System.out.println("Would you like to try again! \n "
						+ "Type 'y' to continue 'n' to exit this menu:\n ");
				String type = this.userIn.nextLine();
				if(type.equals("n")) {
					break;
				}

			}
		}
		
		
		System.out.println("Please press enter to confirm to back to the main menu!");
		this.userIn.nextLine();
	}
	
	//2
	public void manageCustomerAccount(User user) {
		while(true)  {
			List<Object> list = csi.viewCustomerInfo(user);
			if(list == null) {
				System.out.println("Welcome "+ user.getFirstName()+" "+user.getLastName()+"!\n"
						+ "Your account is under review! Please wait!\n");
				System.out.println("Press enter to come back to the main menu!\n");
				this.userIn.nextLine();
				break;
			}
			BankingAccount bankingAccount = new BankingAccount();
			ChequeingAccount chequeingAccount = new ChequeingAccount();
			SavingAccount savingAccount = new SavingAccount();
			for(Object o: list) {
				 if(o instanceof ChequeingAccount) {
					chequeingAccount = (ChequeingAccount) o;
				} else if(o instanceof SavingAccount) {
					savingAccount = (SavingAccount) o;
				} else if(o instanceof BankingAccount) {
					bankingAccount = (BankingAccount)o;
				} 
			}
			
			System.out.println("Welcome "+ user.getFirstName()+" "+user.getLastName()+"!\n");
			
			System.out.println("Select the options below for your account:\n");
			System.out.println("1. View your account details\n"
					         + "2. Deposit money to your account\n"
					         + "3. Withdraw money from your account\n"
					         + "4. View pending transaction\n"
					         + "5. Send money to another account\n"
					         + "6. Return to the main menu\n");
			System.out.println("Your choice is: ");
			int option = this.userIn.nextInt();
			switch(option) {
			    case 1: 
			    	viewCustomerAccountDetail(chequeingAccount,savingAccount);
			    	break;
			    case 2:
			    	depositMoney(bankingAccount,chequeingAccount,savingAccount);
			    	break;
			    case 3: 
			    	withdrawMoney(bankingAccount,chequeingAccount,savingAccount);
			    	break;
			    case 4:
			    	break;
			    case 5:
			    	break;
			    default:
			    	break;
			}
			
			if(option==6) {
				break;
			}
			
			
		} 			
			
		
	}
	
	//2.1
	public void viewCustomerAccountDetail(ChequeingAccount ca,SavingAccount sa) {
		  this.userIn.nextLine();  
		  System.out.println("Your accounts:\n");
		  System.out.println(ca);
		  System.out.println(sa);
		  System.out.println("Please enter to come bank the main menu!\n");
		  this.userIn.nextLine();
	}
	//2.2
	public void depositMoney (BankingAccount ba, ChequeingAccount ca,SavingAccount sa) {
		
		System.out.println("Which account do you need to deposit?\n"
				+ "1. Chequing Account\n"
				+ "2. Saving Account\n");
		int option = this.userIn.nextInt();
		System.out.println("Please enter your amount:\n");
		double amount =this.userIn.nextDouble();
		this.userIn.nextLine();
		if(option ==1) {
			if(csi.deposit(ba.getBankId(),ca, amount))
				System.out.println("You successfully deposit to your account!");		
		} else if(option == 2) {
			if(csi.deposit(ba.getBankId(), sa, amount))
				System.out.println("You successfully deposit to your account!");
		} else {
			System.out.println("Please try again and enter 1 or 2\n");
		}
	  System.out.println("Please enter to come bank the main menu!\n");
	  this.userIn.nextLine();
	}
	//2.3
	public void withdrawMoney(BankingAccount ba, ChequeingAccount ca,SavingAccount sa) {
		System.out.println("Which account do you need to withdraw?\n"
				+ "1. Chequing Account\n"
				+ "2. Saving Account\n");
		int option = this.userIn.nextInt();
		System.out.println("Please enter your amount:\n");
		double amount =this.userIn.nextDouble();
		this.userIn.nextLine();
		if(option ==1) {
			if(csi.withdraw(ba.getBankId(),ca, amount))
				System.out.println("You successfully withraw to your account!");		
		} else if(option == 2) {
			if(csi.withdraw(ba.getBankId(), sa, amount))
				System.out.println("You successfully withraw to your account!");
		} else {
			System.out.println("Please try again and enter 1 or 2\n");
		}
	  System.out.println("Please enter to come bank the main menu!\n");
	  this.userIn.nextLine();
	}
	//2.4
	public void viewPendingTransaction() {
		
	}
	//2.5
	public void sendMoney() {
		
	}
	
	//2.6
	public void manageEmployeeAccount() {
		
	}
	  
	  
	  
	  
	  
	  
}
