package com.ibm.ecoddemo.microservice.servicechoreographer;

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
@Component(value="customersServiceClient")
public class CustomersServiceClient {

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
	private @Value("${customers.service.url.logical}") String serviceUrl;
	
	/*TIP
	 * Use Aspect for Circuit Breaker pattern to be applied if you prefer non intrusive nature to
	 * main only business processes here.*/
	/* 
	 * Spring Cloud automatically wraps Spring beans with the annotation in a proxy that  is connected to
	 * Hystrix circuit braker.  The circuit breaker calculates when to open and close the circuit, and what
	 * to do in case of failure.
	 * */
	@HystrixCommand(fallbackMethod="searchByCustomerNumberFallback")
	public CustomerDTO searchByCustomerNumber(String customerNo) {
		return restTemplate.getForObject(serviceUrl + "/searchby/number/{customerNo}", CustomerDTO.class, customerNo);
	}
	
	/* This method will be called when Customers Service is not available. */
	public CustomerDTO searchByCustomerNumberFallback(String customerNo) {
		CustomerDTO customerNotFound = new CustomerDTO();
		customerNotFound.setCustomerNo("Unable to find due to Customers Service Not Available or Not Found with customer ID " + customerNo);
		return customerNotFound;
	}

	@HystrixCommand(fallbackMethod="searchByNameContainsFallback")
	public List<CustomerDTO> searchByNameContains(String name) {
		CustomerDTO[] customers = null;

		try {
			customers = restTemplate.getForObject(serviceUrl + "/searchby/name/{name}", CustomerDTO[].class, name);
		} catch (HttpClientErrorException e) {
			// Not Found
		}

		if (customers == null || customers.length == 0)
			return null;
		else
			return Arrays.asList(customers);
	}
	
	public List<CustomerDTO> searchByNameContainsFallback(String name) {
		CustomerDTO customerNotFound = new CustomerDTO();
		customerNotFound.setName("Unable to find due to Customers Service Not Available or Not Found with name " + name);
		return Arrays.asList(customerNotFound);
	}
	
}
