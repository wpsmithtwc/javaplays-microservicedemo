package com.ibm.ecoddemo.microservice.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.ibm.ecoddemo.restservice.customers.CustomerRepository;
import com.ibm.ecoddemo.restservice.customers.RestCustomersService;
	
@SpringBootApplication
@EnableDiscoveryClient /* register to the service registry & discovery service by sending heartbeats after instantiated */
@Import(RestCustomersService.class) /* restAccountsService is a separate rest service that can be exposed as a microservice */
public class CustomersService {

	@Autowired
	protected CustomerRepository accountRepository;

	public static void main(String[] args) {
		new SpringApplicationBuilder(CustomersService.class).web(true).run(args);
	}
}
