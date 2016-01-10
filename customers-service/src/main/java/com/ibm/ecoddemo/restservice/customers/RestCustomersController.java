package com.ibm.ecoddemo.restservice.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCustomersController {

	@Autowired
	protected CustomerDAO customerDao;

	@RequestMapping("/test")
	public String testService() {
		return "Test message: Customers Service is up and running";
	}
	
	@RequestMapping("/searchby/number/{customerNo}")
	public CustomerDO byNumber(@PathVariable("customerNo") String customerNo) {
		CustomerDO customer = customerDao.findByCustomerNo(customerNo);

		if (customer == null)
			throw new CustomerNotFoundException(customerNo);
		else {
			return customer;
		}
	}

	@RequestMapping("/searchby/name/{name}")
	public List<CustomerDO> byOwner(@PathVariable("name") String partialName) {

		List<CustomerDO> customers = customerDao.findByNameContainingIgnoreCase(partialName);

		if (customers == null || customers.size() == 0)
			throw new CustomerNotFoundException(partialName);
		else {
			return customers;
		}
	}
}
