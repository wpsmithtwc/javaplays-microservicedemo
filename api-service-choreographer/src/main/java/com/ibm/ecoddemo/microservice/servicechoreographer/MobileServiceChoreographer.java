package com.ibm.ecoddemo.microservice.servicechoreographer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile")
public class MobileServiceChoreographer {
	
	@RequestMapping("/accounts/findby/number/{accountNumber}")
	public String findByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		return "findByAccountNumber reachable";
	}

	@RequestMapping("/accounts/findby/name/{name}")
	public String findByAccountOwnerName(@PathVariable("name") String partialName) {
		return "findByAccountOwnerName reachable";
	}
}
