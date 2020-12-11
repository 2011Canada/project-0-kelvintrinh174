package com.revature.repositories;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserDAO {
       
	   public User saveOne(User user);
	   public User findOne(String email, String password, boolean isCustomer) 
			   throws UserNotFoundException, InternalErrorException, SQLException;
       public List<User> findAll();
       public void updateOne(int userId);
       
}
