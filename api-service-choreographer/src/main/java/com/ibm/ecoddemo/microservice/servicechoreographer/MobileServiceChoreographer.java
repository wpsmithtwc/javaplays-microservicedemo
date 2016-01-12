package com.ibm.ecoddemo.microservice.servicechoreographer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danielcho
 *
 */
@RestController
@RequestMapping("/mobile")
public class MobileServiceChoreographer {
	
	private @Resource(name="accountsServiceClient") AccountsServiceClient accountsServiceClient;
	private @Resource(name="customersServiceClient") CustomersServiceClient customersServiceClient;
	
	@RequestMapping("/accounts/findby/number/{accountNumber}")
	public CustomerAccountDTO findByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		/* a request from mobile device fetches both customer and account information from backend processes. */
		CustomerAccountDTO customerAccount = new CustomerAccountDTO();
		AccountDTO account = accountsServiceClient.searchByNumber(accountNumber);
		customerAccount.addAccounts(account);
		CustomerDTO customer = customersServiceClient.searchByNumber(account.getCustomerNo());
		BeanUtils.copyProperties(customer, customerAccount);
		return customerAccount;
	}

	@RequestMapping("/accounts/findby/name/{name}")
	public List<CustomerAccountDTO> findByAccountOwnerName(@PathVariable("name") String partialName) {
		List<CustomerAccountDTO> customerAccounts = new ArrayList<CustomerAccountDTO>(0);
		CustomerAccountDTO customerAccount = null;
		CustomerDTO customer = null;
		for (AccountDTO account : accountsServiceClient.searchByOwnerContains(partialName)) {
			customer = customersServiceClient.searchByNumber(account.getCustomerNo());
			if (customer != null) {
				customerAccount = new CustomerAccountDTO();
				customerAccount.addAccounts(account);
				BeanUtils.copyProperties(customer, customerAccount);
			}
		}
		
		
		return customerAccounts;
	}
}
