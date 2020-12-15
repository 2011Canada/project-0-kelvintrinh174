package com.revature.repositories;

import java.util.List;

import com.revature.models.Transaction;

public interface TransactionDAO {
	public Transaction saveOne(Transaction transaction);	
	
	public boolean updateOne(Transaction transaction);
	
	public List<Transaction> findRepicient(int userId);
}
