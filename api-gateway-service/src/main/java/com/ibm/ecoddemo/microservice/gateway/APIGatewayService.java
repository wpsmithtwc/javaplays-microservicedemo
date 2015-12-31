package com.ibm.ecoddemo.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableCircuitBreaker
@EnableFeignClients
public class APIGatewayService {

	/*
	 * API Gateway
	 * 1.  UI applications to proxy all calls to a single backend entry.
	 * 2.  Authentication concerns independently for all backend services.
	 * 3.  This proxy uses Ribbon to locate an service instance to forward to using
	 *     Eureka.
	 * 4.  All requests are executed in Hystrix command, so failures can be detected via
	 *     Hystrix Metrics dashboard where once the circuit is opne the proxy won't try to
	 *     contact the service.
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
