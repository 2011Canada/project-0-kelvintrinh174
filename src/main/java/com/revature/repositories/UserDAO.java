package com.revature.repositories;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
       
	   public User saveOne(User user);
	   public User findOne(String email, String password, boolean isCustomer);
       public List<User> findAll();
       public void updateOne(int userId);
       
}
