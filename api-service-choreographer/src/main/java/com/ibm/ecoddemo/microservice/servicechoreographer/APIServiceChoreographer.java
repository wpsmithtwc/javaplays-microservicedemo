package com.ibm.ecoddemo.microservice.servicechoreographer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

/**
 * @author danielcho
 * 
 * API service choreographer provides a set of APIs per client type,
 * 1. To abstract direct systems of record APIs
 * 2. To allow independent evolution between clients and providers without impact
 * 3. To allow facade to systems of record APIs to optimize accesses by different client types
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@PropertySource("classpath:servicenames.properties")
public class APIServiceChoreographer {

	public static void main(String[] args) {
		SpringApplication.run(APIServiceChoreographer.class, args);
	}
}
