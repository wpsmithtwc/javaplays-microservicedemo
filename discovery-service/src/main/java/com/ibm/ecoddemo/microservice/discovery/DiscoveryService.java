package com.ibm.ecoddemo.microservice.discovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryService {

	/* Discovery service:  
	 * Using logical name (service id instead of actual end point,
	 * multiple processes must be able to find each other to collaborate during runtime. */
	public static void main(String[] args) {
		new SpringApplicationBuilder(DiscoveryService.class).web(true).run(args);
	}
}
