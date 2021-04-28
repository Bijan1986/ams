package com.bank.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.CreditCardLimit;
import com.bank.ams.repository.CreditCardLimitRepository;

@Service
public class CreditCardLimitService {

	@Autowired
	private CreditCardLimitRepository creditCardLimitRepository;

	public List<CreditCardLimit> getAllCreditCardLimits() {
		return creditCardLimitRepository.findAll();
	}

}
