package com.bank.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ams.model.ClientType;

/**
 * 
 * @author A182503
 *
 */
@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, String> {

}
