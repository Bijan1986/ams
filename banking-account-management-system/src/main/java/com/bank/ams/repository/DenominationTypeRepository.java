package com.bank.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ams.model.DenominationType;

@Repository
public interface DenominationTypeRepository extends JpaRepository<DenominationType, String> {

}
