package com.ibm.ecoddemo.microservice.servicechoreographer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsServiceChoreographer {
	
	@RequestMapping("/test")
	public String testMessage() {
		return "Accounts Service Choreographer runs properly";
	}
	
	@RequestMapping("/findby/number/{accountNumber}")
	public String findByNumber(@PathVariable("accountNumber") String accountNumber) {
		return "findByNumber reachable";
	}

	@RequestMapping("/findby/owner/{name}")
	public String findByOwner(@PathVariable("name") String partialName) {
		return "findByOwner reachable";
	}
}
