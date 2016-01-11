package com.ibm.ecoddemo.microservice.servicechoreographer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author danielcho
 *
 */
@JsonRootName("Account")
public class AccountDTO {

	private Long id;
	private String acctType;
	private String number;
	private String owner;
	private String customerNo;
	private BigDecimal balance;
	private List<TransactionDTO> transactions;

	/**
	 * Default constructor for JPA only.
	 */
	public AccountDTO() {
		/* setup defualt values */
		id = Long.valueOf(0);
		acctType = "";
		number = "";
		owner = "";
		customerNo = "";
		balance = BigDecimal.ZERO;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String accountNumber) {
		this.number = accountNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public BigDecimal getBalance() {
		return balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public void setBalance(BigDecimal value) {
		balance = value;
		balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public void addTransactions(TransactionDTO transaction) {
		if (transactions == null) {
			transactions = new ArrayList<TransactionDTO>(0);
		}
		transactions.add(transaction);
	}

	public List<TransactionDTO> getTransactions() {
		return transactions;
	}

}
