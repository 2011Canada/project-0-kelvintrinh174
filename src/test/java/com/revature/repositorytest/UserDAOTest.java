package com.revature.repositorytest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserPostgresDAO;

public class UserDAOTest {
	
	UserDAO ud = new UserPostgresDAO();	
	
	@Test
	public void logIn() throws UserNotFoundException, InternalErrorException, SQLException {
		//fail("Not yet implemented");
		
		User user = ud.findOne("quang@gmail.com", "12345", true);
		User userTest = new Customer("quang@gmail.com", "12345","Quang","Trung");
		assertEquals(userTest.getEmail(), user.getEmail());
		assertEquals(userTest.getFirstName(),user.getFirstName());
		
	}
	
	

}
