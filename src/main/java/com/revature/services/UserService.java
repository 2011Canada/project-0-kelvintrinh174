package com.revature.services;

import java.sql.SQLException;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;

public interface UserService {
      public void userLogIn(String email,String password, boolean isCustomer)
      throws UserNotFoundException,InternalErrorException,SQLException;
}
