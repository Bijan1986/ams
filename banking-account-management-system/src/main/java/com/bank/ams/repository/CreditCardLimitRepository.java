package com.bank.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ams.model.ClientAccount;
import com.bank.ams.model.CreditCardLimit;

@Repository
public interface CreditCardLimitRepository extends JpaRepository<CreditCardLimit, ClientAccount> {
	
}
