package com.bank.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ams.model.AtmAllocation;

@Repository
public interface AtmAllocationRepository extends JpaRepository<AtmAllocation, Integer> {

}
