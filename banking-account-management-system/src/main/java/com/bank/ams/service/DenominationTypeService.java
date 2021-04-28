package com.bank.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.DenominationType;
import com.bank.ams.repository.DenominationTypeRepository;

@Service
public class DenominationTypeService {

	@Autowired
	private DenominationTypeRepository denominationTypeRepository;

	public List<DenominationType> getAllDenominationType() {
		return denominationTypeRepository.findAll();
	}

}
