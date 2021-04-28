package com.bank.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.Denomination;
import com.bank.ams.repository.DenominationRepository;

@Service
public class DenominationService {

	@Autowired
	private DenominationRepository denominationRepository;

	public List<Denomination> getAllDenomination() {
		return denominationRepository.findAll();
	}

}
