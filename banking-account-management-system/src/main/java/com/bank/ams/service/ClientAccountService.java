package com.bank.ams.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.ams.model.Client;
import com.bank.ams.model.ClientAccount;
import com.bank.ams.repository.ClientAccountRepository;

@Service
public class ClientAccountService {

	@Autowired
	private ClientAccountRepository clientAccountRepository;

	public List<ClientAccount> getAllClients() {
		return clientAccountRepository.findAll();
	}

	public List<ClientAccount> getAllClientAccountByClientId(Client client) {
		List<ClientAccount> allClients = getAllClients();
		List<ClientAccount> selectedAccounts = allClients.stream().filter(i -> i.getClient().equals(client))
				.collect(Collectors.toList());
		selectedAccounts.sort((item1, item2) -> item2.getDisplayBalance().compareTo(item1.getDisplayBalance()));
		return selectedAccounts;
	}

	public ClientAccount getClientAccountByClientIdAndAccNumb(String accountNumber) {
		List<ClientAccount> allClients = getAllClients();
		return allClients.stream().filter(i -> i.getClientAccountNumber().equals(accountNumber)).findFirst().get();
	}

	public void saveClientAccount(ClientAccount clientAccount) {
		clientAccountRepository.save(clientAccount);
	}

}
