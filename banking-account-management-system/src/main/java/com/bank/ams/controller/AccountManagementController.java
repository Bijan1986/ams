package com.bank.ams.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.ams.model.Client;
import com.bank.ams.model.ClientAccount;

@Controller
@RequestMapping("/")
public class AccountManagementController {

	@Autowired
	private BankController bankController;

	@GetMapping("/")
	public String searchCustomer(Model theModel) {
		theModel.addAttribute("client", new Client());
		return "index";
	}

	@GetMapping("/customer")
	public String showCustomer(@ModelAttribute("client") Client client, Model model) {
		Client selectedClient = bankController.getClientById(client.getClientId());
		model.addAttribute("selectedClient", selectedClient);
		List<ClientAccount> allAccounts = bankController.getAllClientAccountsById(selectedClient.getClientId());
		allAccounts.forEach(t->System.out.println("Account Number "+t.getClientAccountNumber()));
		return "customerDetails";
	}

}
