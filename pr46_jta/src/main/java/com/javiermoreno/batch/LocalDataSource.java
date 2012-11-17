package com.javiermoreno.batch;

import java.sql.Connection;
import java.sql.SQLException;

import javax.transaction.TransactionManager;

import org.enhydra.jdbc.standard.StandardXADataSource;
import org.hibernate.transaction.JOTMTransactionManagerLookup;


public class LocalDataSource 
extends StandardXADataSource {

	private static JOTMTransactionManagerLookup txML =
		new JOTMTransactionManagerLookup();
	
	@Override
	public synchronized Connection getConnection() 
	throws SQLException {
		if (super.getTransactionManager() == null) {
			TransactionManager tm =
				txML.getTransactionManager(null);
			super.setTransactionManager(tm);
		}
		return super.getXAConnection().getConnection();
	}
	
	
}
