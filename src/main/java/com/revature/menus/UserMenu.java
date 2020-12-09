package com.revature.menus;

import com.revature.services.CustomerServiceImplementation;
import com.revature.services.EmployeeServiceImplementation;

public class UserMenu implements Displayable {
		
	  private CustomerServiceImplementation csi;
	  private EmployeeServiceImplementation esi;
	  
	  public UserMenu(CustomerServiceImplementation csi) {
		  this.csi = csi;
	  }
	  
	  public UserMenu(EmployeeServiceImplementation esi) {
		  this.esi = esi;
	  }

	public String display() {
		// TODO Auto-generated method stub
		return null;
	}
	  
	  
	  
	  
	  
	  
	  
	  
}
