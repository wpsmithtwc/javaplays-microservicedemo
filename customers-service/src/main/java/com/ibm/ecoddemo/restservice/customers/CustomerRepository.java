package com.ibm.ecoddemo.restservice.customers;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface CustomerRepository extends Repository<CustomerDO, Long> {

	public CustomerDO findByNumber(String customerId);

	public List<CustomerDO> findByOwnerContainingIgnoreCase(String partialName);

	@Query("SELECT count(*) from CustomerDO")
	public int countAccounts();
}
