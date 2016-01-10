package com.ibm.ecoddemo.microservice.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.ibm.ecoddemo.restservice.accounts.AccountDAO;
import com.ibm.ecoddemo.restservice.accounts.RestAccountsService;
	
@SpringBootApplication
@EnableDiscoveryClient /* register to the service registry & discovery service by sending heartbeats after instantiated */
@Import(RestAccountsService.class) /* restAccountsService is a separate rest service that can be exposed as a microservice */
public class AccountsService {

	@Autowired
	protected AccountDAO accountDao;

	public static void main(String[] args) {
		new SpringApplicationBuilder(AccountsService.class).web(true).run(args);
	}
}
