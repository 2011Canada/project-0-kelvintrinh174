package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserService {
      public User userLogIn(String email,String password, boolean isCustomer)
      throws UserNotFoundException,InternalErrorException,SQLException;
      
      public List<Object> viewCustomerInfo(User customer);
}
