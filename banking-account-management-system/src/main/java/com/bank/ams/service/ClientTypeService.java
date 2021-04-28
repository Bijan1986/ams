package com.bank.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.ClientType;
import com.bank.ams.repository.ClientTypeRepository;

@Service
public class ClientTypeService {
	
	@Autowired
	private ClientTypeRepository clientTypeRepository;
	
	public List<ClientType> getAllClients(){
		return clientTypeRepository.findAll();
	}

}
