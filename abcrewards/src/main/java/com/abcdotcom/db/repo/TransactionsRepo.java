package com.abcdotcom.db.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abcdotcom.db.entities.CustomerTransactions;

@Repository
public interface TransactionsRepo extends CrudRepository<CustomerTransactions, Long> {
	List<CustomerTransactions> findByCustomerId(String customerId);
	
	List<CustomerTransactions> findAll();
	
	CustomerTransactions save(CustomerTransactions custTx);
}
