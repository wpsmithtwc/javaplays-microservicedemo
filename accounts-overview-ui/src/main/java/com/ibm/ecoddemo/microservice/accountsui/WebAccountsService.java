package com.ibm.ecoddemo.microservice.accountsui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @author danielcho
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@PropertySource("classpath:servicenames.properties")
public class WebAccountsService {

	public static void main(String[] args) {
		new SpringApplicationBuilder(WebAccountsService.class).web(true).run(args);
	}

}
