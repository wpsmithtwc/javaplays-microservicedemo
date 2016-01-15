package com.ibm.ecoddemo.microservice.discovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/***
 * 
 * @author danielcho
 * 
 * PURPOSE
 * 
 * DRIVING FORCE
 * 
 */

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryService extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DiscoveryService.class);
    }
	
	/* Discovery service:  
	 * Using logical name (service id instead of actual end point,
	 * multiple processes must be able to find each other to collaborate during runtime. */
	public static void main(String[] args) {
		new SpringApplicationBuilder(DiscoveryService.class).web(true).run(args);
	}
}
