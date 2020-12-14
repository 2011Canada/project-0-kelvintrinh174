package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.models.User;

public interface EmployeeService {
    
	public List<User> viewListPendingUser() throws InternalErrorException, SQLException;
	public boolean setCustomerStatus();
	public void viewCustomerAccount(String email);
}
