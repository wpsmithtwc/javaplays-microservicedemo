package com.ibm.ecoddemo.microservice.accountsui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@ComponentScan(useDefaultFilters = false)
public class WebAccountsService {

	/* Uses the logical account service name registered with Eureka discovery service, not
	 * an actual host to avoid direct endpoint integration since a target cloud service location 
	 * can change any time when auto-scaling. 
	 * */
	public static final String ACCOUNTS_SERVICE_URL = "ACCOUNTS-SERVICE";

	public static void main(String[] args) {
		new SpringApplicationBuilder(WebAccountsService.class).web(true).run(args);
	}

	@Bean
	public RestAccountsServiceClient accountsService() {
		return new RestAccountsServiceClient(ACCOUNTS_SERVICE_URL);
	}

	@Bean
	public WebAccountsController accountsController() {
		return new WebAccountsController(accountsService());
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}
}
