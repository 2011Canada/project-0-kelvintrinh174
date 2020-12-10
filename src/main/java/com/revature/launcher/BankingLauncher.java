package com.revature.launcher;

import com.revature.menus.UserMenu;
import com.revature.repositories.UserImplementationDAO;
import com.revature.services.CustomerServiceImplementation;
import com.revature.services.EmployeeServiceImplementation;

public class BankingLauncher {

	public static void main(String[] args) {
		
		UserImplementationDAO uid = new UserImplementationDAO();
		CustomerServiceImplementation csi = new CustomerServiceImplementation(uid);
		EmployeeServiceImplementation esi = new EmployeeServiceImplementation(uid);
		UserMenu userMenu = new UserMenu(csi,esi);

		
		
		System.out.println("Welcome to the bank 101!\n");
		while(true) {
							
			userMenu.manageUserAccountInput();
			System.out.println("\n");
		}
		

	    
	}

}
