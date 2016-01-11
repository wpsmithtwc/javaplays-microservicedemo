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
	
	private @Resource(name="accountsServiceClient") AccountsServiceClient accountServiceClient;
	
	@RequestMapping("/accounts/findby/number/{accountNumber}")
	public AccountDTO findByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		return accountServiceClient.searchByNumber(accountNumber);
	}

	@RequestMapping("/accounts/findby/name/{name}")
	public List<AccountDTO> findByAccountOwnerName(@PathVariable("name") String partialName) {
		return accountServiceClient.searchByOwnerContains(partialName);
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
