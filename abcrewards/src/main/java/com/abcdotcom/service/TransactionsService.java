package com.abcdotcom.service;

import java.util.List;

import com.abcdotcom.db.entities.CustomerTransactions;

public interface TransactionsService {

	public List<CustomerTransactions> getAllTransactions();

	public List<CustomerTransactions> getTransactionsByCustomerId(String customerId);

	public CustomerTransactions add(CustomerTransactions custTx);
}
