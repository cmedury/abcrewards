package com.abcdotcom.db.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CUSTOMER_TRANSACTIONS")
@Getter
@Setter
@NoArgsConstructor
public class CustomerTransactions implements Serializable {

	private static final long serialVersionUID = 1652289289608931236L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, name = "CUSTOMER_ID")
	private String customerId;
	@Column(nullable = false, name = "TRANSACTION_DATE")
	private LocalDate transactionDate;
	@Column(nullable = false, name = "TRANSACTION_AMOUNT")
	private Double transactionAmount;

	public CustomerTransactions(String customerId, LocalDate transactionDate, Double transactionAmount) {
		this.customerId = customerId;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
	}

}
