package com.ibm.ecoddemo.microservice.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationService {

	/* PURPOSE
	 * This microservice serves as REST based API to provide external configuration.
	 * 
	 * DRIVING FORCE
	 * 1. Versioned configuration management in distribution system
	 * 2. Avoid service outage to update configuration.
	 **/
	public static void main(String[] args) {
		SpringApplication.run(ConfigurationService.class, args);
	}
}
