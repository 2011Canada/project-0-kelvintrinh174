package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.utilities.ConnectionFactory;

public class UserPostgresDAO implements UserDAO {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	

	public User saveOne(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User findOne(String email, String password, boolean isCustomer) throws UserNotFoundException, InternalErrorException, SQLException{
		
		Connection conn = cf.getConnection();
		
		try {
			String sql = "select * from \"user\" where email = ? and password = ?;";
			PreparedStatement getUser = conn.prepareStatement(sql);
			
			getUser.setString(1,email);
			getUser.setString(2,password);

			ResultSet res = getUser.executeQuery();
			
			if(res.next()) {
				User u;
				if(isCustomer) {
					u = new Customer();
				} else {
					u = new Employee();
				}
				u.setUserId(res.getInt("user_id"));
				u.setFirstName(res.getString("first_name"));
				u.setLastName(res.getString("last_name"));
				u.setEmail(res.getString("email"));
				u.setPassword(res.getString("password"));
				return u;
			}
			else {
				throw new UserNotFoundException();
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
		
		
		
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateOne(int userId) {
		// TODO Auto-generated method stub

	}

}
