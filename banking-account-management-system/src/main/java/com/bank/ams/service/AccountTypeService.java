package com.bank.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.AccountType;
import com.bank.ams.repository.AccountTypeRepository;

@Service
public class AccountTypeService {

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	public List<AccountType> getAllAccountType() {
		return accountTypeRepository.findAll();
	}

}
