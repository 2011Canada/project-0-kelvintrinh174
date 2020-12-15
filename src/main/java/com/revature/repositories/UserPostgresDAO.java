package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.BankingAccount;
import com.revature.models.BankingStatus;
import com.revature.models.ChequeingAccount;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.SavingAccount;
import com.revature.models.User;
import com.revature.utilities.ConnectionFactory;

public class UserPostgresDAO implements UserDAO {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
    //create new customer's account
	public User createCustomerAccount(User user, double balance) {
		// TODO Auto-generated method stub
		
		Connection conn = cf.getConnection();
		try {
			
			conn.setAutoCommit(false);
			String sql = "insert into \"user\" (\"email\",\"password\",\"first_name\",\"last_name\",\"isCustomer\",\"user_status\")\r\n"
					+ "values(?,?,?,?,true,'PENDING') returning \"user_id\";";
			
			PreparedStatement createCustomerAccount = conn.prepareStatement(sql);
			createCustomerAccount.setString(1, user.getEmail());
			createCustomerAccount.setString(2, user.getPassword());
			createCustomerAccount.setString(3, user.getFirstName());
			createCustomerAccount.setString(4, user.getLastName());
			
			
			
			ResultSet res = createCustomerAccount.executeQuery();
			
			int newCustomerId;
			if(res.next()) {
				newCustomerId = res.getInt("user_id");
				
			} else {
				throw new SQLException();
			}
			
			//do st with banking account
			String bankingSql = "insert into \"banking_account\" (customer_id,pending_transaction,mailing_address,banking_status,initial_deposit)\r\n"
					+ "values(?,false,'Not Available','PENDING',?);";
			
			PreparedStatement applyNewBanking = conn.prepareStatement(bankingSql);
			applyNewBanking.setInt(1, newCustomerId);
			applyNewBanking.setDouble(2, balance);
			
			applyNewBanking.execute();
			
			
		} catch (SQLException e ) {
			  e.printStackTrace();
			  try {
				  	conn.rollback();
			  } catch(SQLException e1) {
				  e1.printStackTrace();
			  }
		} finally {
			
			 try {
				 conn.commit();
				 conn.setAutoCommit(true);
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }
			 
			 try {
				cf.releaseConnection(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return user;
	}
				
	public List<User> findPendingCustomer()
			throws InternalErrorException, SQLException {
		Connection conn = cf.getConnection();
		List<User> listPendingUser = new ArrayList<User>();
		
		try {
			String sql = "select * from \"user\" where \"user_status\" = 'PENDING';";
			PreparedStatement getPendingUser = conn.prepareStatement(sql);
			ResultSet res = getPendingUser.executeQuery();
			
			if(res.next()) {
				User u = new Customer();
				u.setUserId(res.getInt("user_id"));
				u.setFirstName(res.getString("first_name"));
				u.setLastName(res.getString("last_name"));
				u.setEmail(res.getString("email"));
				
				listPendingUser.add(u);
			} 
			
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
			
		} finally {
			cf.releaseConnection(conn);
		}
		
		
		return listPendingUser;
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
					u = new Customer(res.getString("user_status"));
				} else {
					u = new Employee();
				}
				u.setUserId(res.getInt("user_id"));
				u.setFirstName(res.getString("first_name"));
				u.setLastName(res.getString("last_name"));
				u.setEmail(res.getString("email"));
				
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


	public List<Object> findCustomerInfoByEmail(User user) throws InternalErrorException
	{
		Connection conn = cf.getConnection();
		List<Object> listCustomerInfo = new ArrayList<Object>();

		
		try {
			String sql = "select u.user_id,u.email, u.first_name ,u.last_name, u.user_status,\r\n"
					+ "ba.banking_status,ba.pending_transaction, ba.bank_id,\r\n"
					+ "ca.ca_account_number,ca.ca_balance,\r\n"
					+ "sa.sa_account_number,sa.sa_balance\r\n"
					+ "\r\n"
					+ "from \"user\" u \r\n"
					+ "inner join banking_account ba on u.user_id = ba.customer_id \r\n"
					+ "inner join chequing_account ca on ba.bank_id = ca.bank_id \r\n"
					+ "inner join saving_account sa on ba.bank_id = sa.bank_id \r\n"
					+ "where u.\"email\" = ? \r\n"
					+ "and u.user_status = 'ACTIVE' \r\n"
					+ "and ba.banking_status = 'ACTIVE';";
		
			PreparedStatement getCustomerInfo = conn.prepareStatement(sql);
			
			getCustomerInfo.setString(1,user.getEmail());
			
			ResultSet res = getCustomerInfo.executeQuery();
			
			if(res.next()) {
				User customer = new Customer();
				BankingAccount bankingAccount = new BankingAccount();
				ChequeingAccount chequeingAccount =new ChequeingAccount();
				SavingAccount savingAccount = new SavingAccount();
				customer.setUserId(res.getInt("user_id"));
				customer.setFirstName(res.getString("first_name"));
				customer.setLastName(res.getString("last_name"));
				customer.setEmail(res.getString("email"));
			    
				
				listCustomerInfo.add(customer);
				bankingAccount.setBankId(res.getInt("bank_id"));
				bankingAccount.setBankingStatus(res.getString("banking_status"));
				bankingAccount.setPendingTransaction(res.getBoolean("pending_transaction"));
				
				listCustomerInfo.add(bankingAccount);
				chequeingAccount.setAccountNumber(res.getString("ca_account_number"));
				chequeingAccount.setBalance(res.getDouble("ca_balance"));
				
				listCustomerInfo.add(chequeingAccount);
				savingAccount.setAccountNumber(res.getString("sa_account_number"));
				savingAccount.setBalance(res.getDouble("sa_balance"));
				listCustomerInfo.add(savingAccount);
				
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
			
		} finally {
			try {
				cf.releaseConnection(conn);
				//conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		 return listCustomerInfo;
	}


	
}
