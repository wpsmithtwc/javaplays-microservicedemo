package com.ibm.ecoddemo.microservice.hystrix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableHystrixDashboard
@Controller
public class CircuitBreakerMonitoringService {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CircuitBreakerMonitoringService.class).web(true).run(args);
	}

	@RequestMapping("/")
	public String home() {
		return "forward:/hystrix";
	}
	
}
