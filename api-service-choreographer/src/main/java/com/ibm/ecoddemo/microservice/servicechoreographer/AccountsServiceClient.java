package com.ibm.ecoddemo.microservice.servicechoreographer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * @author danielcho
 *
 */
@Component
public class AccountsServiceClient {

	/* Purpose: RestTemplate
	 * 1. Auto-configured by Spring Cloud to use a custom HttpRequestClient using Netflix Ribbon
	 *    to lookup micro-service.  Ribbon acts as a load balancer when multiple service instances are available.
	 * 2. Used in lieu of Feign Client, a declarative service client since it's easy to explicitly set Hystrix fallback
	 *    and automatic DO to DTO mapping to avoid extra coding.
	 * */
	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	/* injects accounts logical service id from servicename.properties
	 * initially registered to service registry, Eureka in this demo */
	private @Value("${accounts.service.url.logical}") String serviceUrl;
	
	/*TIP
	 * Use Aspect for Circuit Breaker pattern to be applied if you prefer non intrusive nature to
	 * main only business processes here.*/
	/* 
	 * Spring Cloud automatically wraps Spring beans with the annotation in a proxy that  is connected to
	 * Hystrix circuit braker.  The circuit breaker calculates when to open and close the circuit, and what
	 * to do in case of failure.
	 * */
	@HystrixCommand(fallbackMethod="searchByNumberFallback")
	public AccountDTO searchByNumber(String accountNumber) {
		return restTemplate.getForObject(serviceUrl + "/searchby/number/{accountNumber}", AccountDTO.class, accountNumber);
	}
	
	/* This method will be called when Accounts Service is not available. */
	public AccountDTO searchByNumberFallback(String accountNumber) {
		/* setup a fallback empty account */
		AccountDTO accountNotFound = new AccountDTO();
		accountNotFound.setId(0);
		accountNotFound.setAcctType("");
		accountNotFound.setNumber(accountNumber);
		accountNotFound.setOwner("Unable to find due to Accounts Service Not Available or Not Found");
		accountNotFound.setCustomerNo("");
		accountNotFound.setBalance(BigDecimal.ZERO);
		
		TransactionDTO transactionNotFound = new TransactionDTO();
		transactionNotFound.setId(0);
		transactionNotFound.setAcctNumber(accountNumber);
		transactionNotFound.setTransDate("");
		transactionNotFound.setPostDate("");
		transactionNotFound.setMerchant("Unalbe to find due to Accounts Service Not Available or Not Found");
		transactionNotFound.setAmount(BigDecimal.ZERO);
		accountNotFound.addTransactions(transactionNotFound);
		return accountNotFound;
	}

	public List<AccountDTO> searchByOwnerContains(String name) {
		AccountDTO[] accounts = null;

		try {
			accounts = restTemplate.getForObject(serviceUrl + "/accounts/owner/{name}", AccountDTO[].class, name);
		} catch (HttpClientErrorException e) {
			// Not Found
		}

		if (accounts == null || accounts.length == 0)
			return null;
		else
			return Arrays.asList(accounts);
	}
}
