package com.ibm.ecoddemo.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class APIGatewayService {

	/*
	 * API Gateway
	 * 1.  UI applications to proxy all calls to a single backend entry.
	 * 2.  Authentication concerns independently for all backend services.
	 * 
	 * Driving Forces
	 * 1.  All participating microservices in the same cluster yet distributed working as one business application.
	 * 2.  Behavior and locations of microservices [components] will constantly change.
	 * 3.  Minimize impact caused by backend processes changes to the client [layer isolation for independent evolution].
	 * 4.  Avoid Cross Origin Resource Sharing security constraints.
	 * */
	public static void main(String[] args) {
		SpringApplication.run(APIGatewayService.class, args);
	}
}
