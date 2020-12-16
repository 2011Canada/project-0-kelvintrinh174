package com.revature.launcher;

import java.sql.SQLException;
import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.menus.UserMenu;
import com.revature.repositories.BankingAccountPostgresDAO;
import com.revature.repositories.TransactionPosgresDAO;
import com.revature.repositories.UserPostgresDAO;
import com.revature.services.CustomerServiceImplementation;
import com.revature.services.EmployeeServiceImplementation;


public class BankingLauncher {
    
	public static Logger p0174Logger = LogManager.getLogger("com.revature.p0174");
	public static void main(String[] args) 
			throws UserNotFoundException, InternalErrorException, 
SQLException, InputMismatchException {
		
		UserPostgresDAO uid = new UserPostgresDAO();
		BankingAccountPostgresDAO bad = new BankingAccountPostgresDAO();
		TransactionPosgresDAO tpd = new TransactionPosgresDAO();
		CustomerServiceImplementation csi = new CustomerServiceImplementation(uid,bad,tpd);
		EmployeeServiceImplementation esi = new EmployeeServiceImplementation(uid);
		UserMenu userMenu = new UserMenu(csi,esi);

		p0174Logger.info("Server has started!");
		System.out.println("Welcome to the bank 101!\n");
		while(true) {
							
			userMenu.manageUserAccountInput();
			System.out.println("\n");
		}
		

	    
	}

}
