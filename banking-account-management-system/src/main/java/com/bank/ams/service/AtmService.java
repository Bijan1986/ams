package com.bank.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.Atm;
import com.bank.ams.repository.AtmRepository;

@Service
public class AtmService {

	@Autowired
	private AtmRepository atmRepository;

	public List<Atm> getAllAtms() {
		return atmRepository.findAll();
	}

}
