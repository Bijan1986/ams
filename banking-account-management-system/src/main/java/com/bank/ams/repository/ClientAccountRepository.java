package com.bank.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ams.model.ClientAccount;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount, String>{

}
