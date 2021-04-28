package com.bank.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.AtmAllocation;
import com.bank.ams.repository.AtmAllocationRepository;

@Service
public class AtmAllocationService {

	@Autowired
	private AtmAllocationRepository atmAllocationRepository;

	public List<AtmAllocation> getAllAtmAllocation() {
		return atmAllocationRepository.findAll();
	}

}
