package com.ibm.ecoddemo.microservice.accountsui;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author danielcho
 *
 */
@JsonRootName("Transaction")
public class TransactionDTO {

	protected Long id;
	protected String acctNumber;
	protected String transDate;
	protected String postDate;
	protected String merchant;
	protected BigDecimal amount;

	protected TransactionDTO() {
		/* setup default value */
		id = Long.valueOf(0);
		acctNumber = "";
		transDate = "";
		postDate = "";
		merchant = "";
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

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public BigDecimal getAmount() {
		return amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

}
