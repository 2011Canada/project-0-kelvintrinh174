package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public class UserMemoryDAO implements CustomerDAO, EmployeeDAO{
		
	   static List<User> listUser = new ArrayList<User>();
	   
	   static {
		   
		   User customerKelvin = new Customer("kelvintrinh@gmail.com","12345");
		   User customerNick = new Customer("nick@gmail.com","12345");
		   User bankEmployee = new Employee("employeeTD001@td.ca","12345");
		   
		   listUser.add(customerKelvin);
		   listUser.add(customerNick);
		   listUser.add(bankEmployee);
		}

		public boolean setCustomerStatus() {
			// TODO Auto-generated method stub
			return false;
		}
	
		public void viewCustomerAccount() {
			// TODO Auto-generated method stub
			
		}
	
		public void viewBalance() {
			// TODO Auto-generated method stub
			
		}
	
		public void applyNewAccount(User customer) {
			// TODO Auto-generated method stub
			
		}
	
		public void applyNewAccountWithBalance(User customer, double balance) {
			// TODO Auto-generated method stub
			
		}
	
		public boolean transferMoney(String email, double amount) {
			// TODO Auto-generated method stub
			return false;
		}
	
		public boolean acceptMoneyTransfer() {
			// TODO Auto-generated method stub
			return false;
		}
		   
	   
	   
}
