package com.ibm.ecoddemo.restservice.accounts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class TransactionDO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Long nextId = 0L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;

	@Column(name= "ACCTNUMBER")
	protected String acctNumber;

	@Column(name = "TRANSDATE")
	protected Date transDate;
	
	@Column(name = "POSTDATE")
	protected Date postDate;
	
	@Column(name = "MERCHANT")
	protected String merchant;

	@Column(name = "AMOUNT")
	protected BigDecimal amount;

	protected TransactionDO() {
		amount = BigDecimal.ZERO;
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}
	
	public String getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public BigDecimal getAmount() {
		return amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ACCTNUMBER", referencedColumnName="NUMBER", insertable=false, updatable=false)
	private AccountDO account;

	public AccountDO getAccount() {
		return account;
	}

	public void setAccount(AccountDO account) {
		this.account = account;
		if (!account.getTransactions().contains(this)) {
			account.getTransactions().add(this);
		}
	}
	

}
