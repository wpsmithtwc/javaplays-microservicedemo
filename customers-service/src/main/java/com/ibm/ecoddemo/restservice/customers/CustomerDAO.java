package com.ibm.ecoddemo.restservice.customers;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface CustomerDAO extends Repository<CustomerDO, Long> {

	public CustomerDO findByCustomerNo(String customerNo);

	public List<CustomerDO> findByNameContainingIgnoreCase(String partialName);

	@Query("SELECT count(*) from CustomerDO")
	public int countCustomers();
}
