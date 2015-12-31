package com.ibm.ecoddemo.restservice.accounts;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface AccountRepository extends Repository<AccountDO, Long> {

	public AccountDO findByNumber(String accountNumber);

	public List<AccountDO> findByOwnerContainingIgnoreCase(String partialName);

	@Query("SELECT count(*) from AccountDO")
	public int countAccounts();
}
