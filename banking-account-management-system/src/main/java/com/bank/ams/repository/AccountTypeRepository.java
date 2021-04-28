package com.bank.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ams.model.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, String>{

}
