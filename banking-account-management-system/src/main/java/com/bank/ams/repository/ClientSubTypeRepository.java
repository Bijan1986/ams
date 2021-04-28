package com.bank.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ams.model.ClientSubType;

@Repository
public interface ClientSubTypeRepository extends JpaRepository<ClientSubType, String> {

}
