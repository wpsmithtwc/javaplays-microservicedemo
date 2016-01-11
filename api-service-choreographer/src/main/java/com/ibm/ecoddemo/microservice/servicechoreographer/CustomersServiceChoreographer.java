package com.ibm.ecoddemo.microservice.servicechoreographer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersServiceChoreographer {
	
	@RequestMapping("/test")
	public String testMessage() {
		return "Customers Service Choreographer runs properly";
	}
	
	@RequestMapping("/findby/number/{customerNo}")
	public String findByNumber(@PathVariable("accountNumber") String accountNumber) {
		return "findByNumber reachable";
	}

	@RequestMapping("/findby/name/{name}")
	public String findByOwner(@PathVariable("name") String partialName) {
		return "findByOwner reachable";
	}
}
