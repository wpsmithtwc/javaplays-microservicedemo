package com.ibm.ecoddemo.restservice.accounts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class AccountDO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Long nextId = 0L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@Column(name= "ACCTTYPE")
	protected String acctType;

	@Column(name= "NUMBER")
	protected String number;

	@Column(name = "OWNER")
	protected String owner;

	@Column(name = "BALANCE")
	protected BigDecimal balance;

	protected AccountDO() {
		balance = BigDecimal.ZERO;
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	
	public String getNumber() {
		return number;
	}

	protected void setNumber(String accountNumber) {
		this.number = accountNumber;
	}

	public String getOwner() {
		return owner;
	}

	protected void setOwner(String owner) {
		this.owner = owner;
	}

	public BigDecimal getBalance() {
		return balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public void withdraw(BigDecimal amount) {
		balance.subtract(amount);
	}

	public void deposit(BigDecimal amount) {
		balance.add(amount);
	}

	@OneToMany(targetEntity=TransactionDO.class, mappedBy="account", fetch=FetchType.LAZY)
	private List<TransactionDO> transactions;
	
	public void addTransactions(TransactionDO transaction) {
		if (transactions == null) {
			transactions = new ArrayList<TransactionDO>(0);
		}
		transactions.add(transaction);
		if (transaction.getAccount() != this) {
			transaction.setAccount(this);
		}
	}

	public List<TransactionDO> getTransactions() {
		return transactions;
	}
	

}
