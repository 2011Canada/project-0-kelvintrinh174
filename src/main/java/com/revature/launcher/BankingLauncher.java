package com.revature.launcher;

import com.revature.repositories.UserImplementationDAO;
import com.revature.services.CustomerService;
import com.revature.services.CustomerServiceImplementation;

public class BankingLauncher {

	public static void main(String[] args) {
		
		UserImplementationDAO uid = new UserImplementationDAO();
		CustomerServiceImplementation csi = new CustomerServiceImplementation(uid);
		csi.userLogIn("kelvintrinh@gmail.com","12345", true);
	
	}

}
