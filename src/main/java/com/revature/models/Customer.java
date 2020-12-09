package com.revature.models;

public class Customer extends User {
			  	 
		private CustomerStatus customerStatus;
	  
		public Customer(String email, String password) {			
			super(email, password);
			super.setCustomer(true);
			
		}


		public CustomerStatus getCustomerStatus() {
			return customerStatus;
		}

		public void setCustomerStatus(CustomerStatus customerStatus) {
			this.customerStatus = customerStatus;
		}

		  	  
}
