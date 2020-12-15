package com.revature.repositories;

import com.revature.exceptions.InternalErrorException;
import com.revature.models.BankingAccount;
import com.revature.models.ChequeingAccount;
import com.revature.models.Transaction;

public class TransactionPostGresDAODebugger {

	public static void main(String[] args) throws InternalErrorException {
		// TODO Auto-generated method stub
		TransactionPosgresDAO tpd = new TransactionPosgresDAO();
		BankingAccount ba = new ChequeingAccount();
		Transaction tsc = new Transaction();
//		tsc.setRepicientEmail("quang@gmail.com");
//		tsc.setSenderAccountNumber("654456");
//		tsc.setSenderId(2);
//		tsc.setTransactionAmount(250);
//	    tpd.saveOne(tsc);
		//tpd.findRepicient(4);
		System.out.println(tpd.findRepicient(4));
	}

}
