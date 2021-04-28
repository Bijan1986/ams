package com.bank.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.ams.model.AccountType;
import com.bank.ams.model.Atm;
import com.bank.ams.model.AtmAllocation;
import com.bank.ams.model.Client;
import com.bank.ams.model.ClientAccount;
import com.bank.ams.model.ClientSubType;
import com.bank.ams.model.ClientType;
import com.bank.ams.model.CreditCardLimit;
import com.bank.ams.model.Currency;
import com.bank.ams.model.CurrencyConversionrate;
import com.bank.ams.model.Denomination;
import com.bank.ams.model.DenominationType;
import com.bank.ams.service.AccountTypeService;
import com.bank.ams.service.AtmAllocationService;
import com.bank.ams.service.AtmService;
import com.bank.ams.service.ClientAccountService;
import com.bank.ams.service.ClientService;
import com.bank.ams.service.ClientSubTypeService;
import com.bank.ams.service.ClientTypeService;
import com.bank.ams.service.CreditCardLimitService;
import com.bank.ams.service.CurrencyConversionRateService;
import com.bank.ams.service.CurrencyService;
import com.bank.ams.service.DenominationService;
import com.bank.ams.service.DenominationTypeService;

@RestController
@RequestMapping("/api")
public class BankController {

	@Autowired
	private ClientTypeService clientTypeService;

	@Autowired
	private ClientSubTypeService clientSubTypeService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private AccountTypeService accountTypeService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private CurrencyConversionRateService currencyConversionRateService;

	@Autowired
	private ClientAccountService clientAccountService;

	@Autowired
	private CreditCardLimitService creditCardLimitService;

	@Autowired
	private DenominationTypeService denominationTypeService;

	@Autowired
	private DenominationService denominationService;

	@Autowired
	private AtmService atmService;

	@Autowired
	private AtmAllocationService atmAllocationService;

	@GetMapping("/clientTypes")
	public List<ClientType> getAllClientTypes() {
		return clientTypeService.getAllClients();
	}

	@GetMapping("/clientSubTypes")
	public List<ClientSubType> getAllClientSubTypes() {
		return clientSubTypeService.getAllClientSubType();
	}

	@GetMapping("/clients")
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}

	@GetMapping("/clients/{id}")
	public Client getClientById(@PathVariable("id") Integer id) {
		return clientService.getClientById(id).get();
	}

	@GetMapping("/accountTypes")
	public List<AccountType> getAllAccountTypes() {
		return accountTypeService.getAllAccountType();
	}

	@GetMapping("/currencies")
	public List<Currency> getAllCurrencies() {
		return currencyService.getAllCurrency();
	}

	@GetMapping("/currenciesConversionRates")
	public List<CurrencyConversionrate> getAllCurrencyConversions() {
		return currencyConversionRateService.getAllCurrencyConversionRate();
	}

	@GetMapping("/clientAccounts")
	public List<ClientAccount> getAllClientAccounts() {
		return clientAccountService.getAllClients();
	}

	@GetMapping("/clientAccounts/{id}")
	public List<ClientAccount> getAllClientAccountsById(@PathVariable("id") Integer id) {
		Client client = new Client();
		client.setClientId(id);
		return clientAccountService.getAllClientAccountByClientId(client);

	}

	@GetMapping("/creditCardLimits")
	public List<CreditCardLimit> getAllCreditCardLimits() {
		return creditCardLimitService.getAllCreditCardLimits();
	}

	@GetMapping("/denominationTypes")
	public List<DenominationType> getAllDenominationTypes() {
		return denominationTypeService.getAllDenominationType();
	}

	@GetMapping("/denominations")
	public List<Denomination> getAllDenominations() {
		return denominationService.getAllDenomination();
	}

	@GetMapping("/atms")
	public List<Atm> getAllAtms() {
		return atmService.getAllAtms();
	}

	@GetMapping("/atmAllocations")
	public List<AtmAllocation> getAllAtmAllocations() {
		return atmAllocationService.getAllAtmAllocation();
	}

}
