package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;
import com.revature.utilities.ConnectionFactory;

public class TransactionPosgresDAO implements TransactionDAO {
    
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
	
	public Transaction saveOne(Transaction transaction) {
		Connection conn = cf.getConnection();
		
		try {
			conn.setAutoCommit(false);
		    String sqlGetUser = "select \"user_id\" from banking101.\"user\" u where email = ?;";
		    PreparedStatement getUser = conn.prepareStatement(sqlGetUser);
		    
		    getUser.setString(1, transaction.getRepicientEmail());
		    
		    ResultSet res = getUser.executeQuery();
		    int userId;
		    
		    if(res.next()) {
		    	userId = res.getInt("user_id");
				
			} else {
				throw new SQLException();
			}
		   
		    //insert to transaction table
		    String transactionSql = "insert into \"transaction\" \r\n"
		    		+ "(repicient_id,sender_account_number,sender_id,transaction_amount,transaction_status)\r\n"
		    		+ "values(?,?,?,?,'PENDING');";
		    PreparedStatement insertTransaction = conn.prepareStatement(transactionSql);
		    insertTransaction.setInt(1, userId);
		    insertTransaction.setString(2, transaction.getSenderAccountNumber());
		    insertTransaction.setInt(3, transaction.getSenderId());
		    insertTransaction.setDouble(4, transaction.getTransactionAmount());
		    
		    insertTransaction.execute();
		    return transaction;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		
		return null;
	}


	public boolean updateOne(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}


	public List<Transaction> findRepicient(int userId) {
		Connection conn = cf.getConnection();
		List<Transaction> list = new ArrayList<Transaction>();
		
		try {
			
			String sql = "select t.transaction_id,t.repicient_id ,t.transaction_amount,"
					+ "t.transaction_status, u.user_id, u.first_name,u.last_name,u.email\r\n"
					+ "from \"transaction\" t \r\n"
					+ "inner join \"user\" u \r\n"
					+ "on t.sender_id = u.user_id "
					+ "where t.transaction_status = 'PENDING' and t.repicient_id = ? ";
			PreparedStatement getTransaction = conn.prepareStatement(sql);
			getTransaction.setInt(1, userId);
			
			ResultSet res = getTransaction.executeQuery();
			
			
			if(res.next()) {
				Transaction transaction = new Transaction();
				transaction.setSenderEmail(res.getString("email"));
				transaction.setTransactionId(res.getInt("transaction_id"));
				transaction.setSenderId(res.getInt("user_id"));
				transaction.setSenderFirstName(res.getString("first_name"));
				transaction.setSenderLatName(res.getString("last_name"));
				transaction.setRepicientId(res.getInt("repicient_id"));
				transaction.setTransactionAmount(res.getDouble("transaction_amount"));
				transaction.setTransactionStatus(res.getString("transaction_status"));
				list.add(transaction);							
				
				
			} 
			
			return list;
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 try {
					cf.releaseConnection(conn);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
		
		return null;
	}

	
}
