package com.bank.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.ClientSubType;
import com.bank.ams.repository.ClientSubTypeRepository;

@Service
public class ClientSubTypeService {

	@Autowired
	private ClientSubTypeRepository clientSubTypeRepository;

	public List<ClientSubType> getAllClientSubType() {
		return clientSubTypeRepository.findAll();
	}

}
