package com.abcdotcom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcdotcom.db.entities.CustomerTransactions;
import com.abcdotcom.db.repo.TransactionsRepo;

@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	TransactionsRepo txRepo;

	public List<CustomerTransactions> getAllTransactions() {
		return txRepo.findAll();
	}

	public List<CustomerTransactions> getTransactionsByCustomerId(String customerId) {
		return txRepo.findByCustomerId(customerId);
	}

	public CustomerTransactions add(CustomerTransactions custTx) {
		return txRepo.save(custTx);
	}
}
