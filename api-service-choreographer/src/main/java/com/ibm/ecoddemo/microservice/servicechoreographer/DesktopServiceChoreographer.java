package com.ibm.ecoddemo.microservice.servicechoreographer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danielcho
 *
 */
@RestController
public class DesktopServiceChoreographer {
	
	@Autowired
	private AccountsServiceClient accountServiceClient;
	
	@RequestMapping("/accounts/findby/number/{accountNumber}")
	public AccountDTO findByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		return accountServiceClient.searchByNumber(accountNumber);
	}

	@RequestMapping("/accounts/findby/name/{name}")
	public String findByAccountOwnerName(@PathVariable("name") String partialName) {
		return "findByAccountOwnerName reachable";
	}
	
	@RequestMapping("/customers/findby/number/{customerId}")
	public String findByCustomerId(@PathVariable("customerId") String customerId) {
		return "findByCustomerId reachable";
	}

	@RequestMapping("/customers/findby/name/{name}")
	public String findByCustomerName(@PathVariable("name") String partialName) {
		return "findByCustomerName reachable";
	}
}
