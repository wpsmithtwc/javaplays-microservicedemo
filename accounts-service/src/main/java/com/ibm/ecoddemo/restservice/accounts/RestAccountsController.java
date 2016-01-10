package com.ibm.ecoddemo.restservice.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAccountsController {

	protected AccountDAO accountDao;

	@Autowired
	public RestAccountsController(AccountDAO accountDao) {
		this.accountDao = accountDao;
	}

	@RequestMapping("/test")
	public String testService() {
		return "Test message: Accounts Rest Service is up and running";
	}
	
	@RequestMapping("/searchby/number/{accountNumber}")
	public AccountDO searchByNumber(@PathVariable("accountNumber") String accountNumber) {
		AccountDO account = accountDao.findByNumber(accountNumber);

		if (account == null)
			throw new AccountNotFoundException(accountNumber);
		else {
			return account;
		}
	}

	@RequestMapping("/searchby/owner/{name}")
	public List<AccountDO> searchByOwner(@PathVariable("name") String partialName) {

		List<AccountDO> accounts = accountDao
				.findByOwnerContainingIgnoreCase(partialName);

		if (accounts == null || accounts.size() == 0)
			throw new AccountNotFoundException(partialName);
		else {
			return accounts;
		}
	}
}
