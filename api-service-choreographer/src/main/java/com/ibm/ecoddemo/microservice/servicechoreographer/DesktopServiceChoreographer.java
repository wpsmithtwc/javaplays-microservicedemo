package com.ibm.ecoddemo.microservice.servicechoreographer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danielcho
 *
 */
@RestController
public class DesktopServiceChoreographer {
	
	private @Resource(name="accountsServiceClient") AccountsServiceClient accountsServiceClient;
	private @Resource(name="customersServiceClient") CustomersServiceClient customersServiceClient;
	
	@RequestMapping("/accounts/findby/number/{accountNumber}")
	public AccountDTO findByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		return accountsServiceClient.searchByNumber(accountNumber);
	}

	@RequestMapping("/accounts/findby/name/{name}")
	public List<AccountDTO> findByAccountOwnerName(@PathVariable("name") String partialName) {
		return accountsServiceClient.searchByOwnerContains(partialName);
	}
	
	@RequestMapping("/customers/findby/number/{customerNo}")
	public CustomerDTO findByCustomerId(@PathVariable("customerNo") String customerNo) {
		return customersServiceClient.searchByNumber(customerNo);
	}

	@RequestMapping("/customers/findby/name/{name}")
	public List<CustomerDTO> findByCustomerName(@PathVariable("name") String partialName) {
		return customersServiceClient.searchByNameContains(partialName);
	}
}
