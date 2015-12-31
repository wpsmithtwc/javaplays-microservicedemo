package com.ibm.ecoddemo.microservice.accountsui;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class RestAccountsServiceClient {

	/* Auto-configured by Spring Cloud to use a custom HttpRequestClient using Netflix Ribbon
	 * to lookup micro-service.
	 * Ribbon acts as a load balancer when hen having multiple service instances are available.
	 * */
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	public RestAccountsServiceClient(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
	}
	
	/*TIP
	 * Use Aspect for Circuit Breaker pattern to be applied if you prefer non intrusive nature to
	 * main only business processes here.*/
	/* 
	 * Spring Cloud automatically wraps Spring beans with the annotation in a proxy that  is connected to
	 * Hystrix circuit braker.  The circuit breaker calculates when to open and close the circuit, and what
	 * to do in case of failure.
	 * */
	@HystrixCommand(fallbackMethod="findByNumberFallback")
	public AccountDTO findByNumber(String accountNumber) {
		return restTemplate.getForObject(serviceUrl + "/accounts/{number}", AccountDTO.class, accountNumber);
	}
	
	/* This method will be called when Accounts Service is not available. */
	public AccountDTO findByNumberFallback(String accountNumber) {
		AccountDTO defaultAccount = new AccountDTO();
		defaultAccount.setId(0);
		defaultAccount.setNumber(accountNumber);
		defaultAccount.setOwner("Does Not Exist due to Accounts Service Not Available");
		defaultAccount.setBalance(BigDecimal.ZERO);
		return defaultAccount;
	}

	public List<AccountDTO> byOwnerContains(String name) {
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
