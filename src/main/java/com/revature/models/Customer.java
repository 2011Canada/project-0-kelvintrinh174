package com.revature.models;

public class Customer extends User {
			  	 
		private CustomerStatus customerStatus;
	  
		public Customer(String email, String password, String firstName, String lastName) {			
			super(email, password, firstName, lastName);
			super.setCustomer(true);
			
		}
		
		public Customer() {
			super.setCustomer(true);;
		}


		public CustomerStatus getCustomerStatus() {
			return customerStatus;
		}

		public void setCustomerStatus(CustomerStatus customerStatus) {
			this.customerStatus = customerStatus;
		}

		  	  
}
