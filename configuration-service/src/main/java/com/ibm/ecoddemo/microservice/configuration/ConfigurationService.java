package com.ibm.ecoddemo.microservice.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;

/***
 * 
 * @author danielcho
 * 
 * PURPOSE
 * This microervice fetches configurations from a version control management system for other microservices to
 * load when instantiated.
 * 
 * DRIVING FORCE
 * 1. Versioned configuration management in lieu of data base managed if configuration externalization is application 
 *    strategy, which is a common practice.
 * 2. Avoid service outage to update configuration when changes during up time - requires service bus enabled.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigurationService extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConfigurationService.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigurationService.class, args);
	}
}
