package com.bank.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.CurrencyConversionrate;
import com.bank.ams.repository.CurrencyConversionRateRepository;

@Service
public class CurrencyConversionRateService {

	@Autowired
	private CurrencyConversionRateRepository currencyConversionRateRepository;

	public List<CurrencyConversionrate> getAllCurrencyConversionRate() {
		return currencyConversionRateRepository.findAll();
	}

}
