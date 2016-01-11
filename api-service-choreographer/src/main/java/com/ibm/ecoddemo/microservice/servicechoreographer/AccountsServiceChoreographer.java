package com.ibm.ecoddemo.microservice.servicechoreographer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsServiceChoreographer {
	
	@RequestMapping("/api/accounts")
	public String testMessage() {
		return "AccountsServiceChoreographer runs properly";
	}
	
	@RequestMapping("/api/accounts/findby/number/{accountNumber}")
	public String findByNumber(@PathVariable("accountNumber") String accountNumber) {
		return "findByNumber reachable";
	}

	@RequestMapping("/api/accounts/findby/owner/{name}")
	public String findByOwner(@PathVariable("name") String partialName) {
		return "findByOwner reachable";
	}
}
