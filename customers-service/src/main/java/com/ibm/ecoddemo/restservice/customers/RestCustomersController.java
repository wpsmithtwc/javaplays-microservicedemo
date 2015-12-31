package com.ibm.ecoddemo.restservice.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCustomersController {

	protected CustomerRepository customerRepository;

	@Autowired
	public RestCustomersController(CustomerRepository accountRepository) {
		this.customerRepository = accountRepository;
	}

	@RequestMapping("/test")
	public String testService() {
		return "Test message: Customers Service is up and running";
	}
	
	@RequestMapping("/searchby/id/{customerId}")
	public CustomerDO byNumber(@PathVariable("accountNumber") String accountNumber) {
		CustomerDO account = customerRepository.findByNumber(accountNumber);

		if (account == null)
			throw new CustomerNotFoundException(accountNumber);
		else {
			return account;
		}
	}

	@RequestMapping("/searchby/owner/{name}")
	public List<CustomerDO> byOwner(@PathVariable("name") String partialName) {

		List<CustomerDO> accounts = customerRepository
				.findByOwnerContainingIgnoreCase(partialName);

		if (accounts == null || accounts.size() == 0)
			throw new CustomerNotFoundException(partialName);
		else {
			return accounts;
		}
	}
}
