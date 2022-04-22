package com.abcdotcom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcdotcom.db.entities.CustomerTransactions;
import com.abcdotcom.service.TransactionsService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsControllers {
	
	@Autowired
	TransactionsService txService; 

	@GetMapping("/get")
	public List<CustomerTransactions> getAllTransactions()
	{
		return txService.getAllTransactions();
	}
	
	@GetMapping("/get/{customerId}")
	public List<CustomerTransactions> getTransactionsByCustomerId(@PathVariable String customerId)
	{
		return txService.getTransactionsByCustomerId(customerId);
	}
	
	@PostMapping("/add")	
	public CustomerTransactions add(@RequestBody CustomerTransactions custTx)
	{
		return txService.add(custTx);
	}
}
