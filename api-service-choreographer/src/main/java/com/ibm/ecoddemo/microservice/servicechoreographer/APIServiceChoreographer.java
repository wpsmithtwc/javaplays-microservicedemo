package com.ibm.ecoddemo.microservice.servicechoreographer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class APIServiceChoreographer {

	public static void main(String[] args) {
		SpringApplication.run(APIServiceChoreographer.class, args);
	}
}
