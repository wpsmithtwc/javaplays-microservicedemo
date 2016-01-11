package com.ibm.ecoddemo.microservice.servicechoreographer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsServiceChoreographer {
	
	@RequestMapping("/accounts/fidnby/number/{accountNumber}")
	public AccountDTO searchByNumber(@PathVariable("accountNumber") String accountNumber) {
		return new AccountDTO();
	}

	@RequestMapping("/accounts/findby/owner/{name}")
	public List<AccountDTO> searchByOwner(@PathVariable("name") String partialName) {
		List<AccountDTO> accounts = new ArrayList<AccountDTO>(0);
		return accounts;
	}
}
