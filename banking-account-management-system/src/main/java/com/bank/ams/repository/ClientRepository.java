package com.bank.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ams.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
