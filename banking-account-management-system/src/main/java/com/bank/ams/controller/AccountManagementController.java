package com.bank.ams.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.ams.model.Client;
import com.bank.ams.model.ClientAccount;
import com.bank.ams.model.CurrencyConversionrate;
import com.bank.ams.util.CurrencyAccountDetails;

@Controller
@RequestMapping("/")
public class AccountManagementController {

	private Comparator<ClientAccount> byBalance = (cl1, cl2) -> cl2.getDisplayBalance()
			.compareTo(cl1.getDisplayBalance());
	private Comparator<CurrencyAccountDetails> byCurrBalance = (cl1, cl2) -> cl2.getZarAmount()
			.compareTo(cl1.getZarAmount());
	private static final String CURRENCY_ACCOUNT_CODE = "CFCA";
	private static final String EMPTY_VAL = " ";
	private static final double EMPTY_BAL = 0.00;

	// private Function<ClientAccount, CurrencyAccountDetails> currencyFunc =
	// (t,r)-> r.set ;

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
		List<ClientAccount> trnscAccounts = allAccounts.stream()
				.filter(account -> account.getAccountTypeCode().getTransactional()).collect(Collectors.toList());
		trnscAccounts.sort(byBalance);
		model.addAttribute("accounts", trnscAccounts);
		model.addAttribute("currencyAccounts", currencyAccountBalanceDetails(allAccounts));
		return "customerDetails";
	}

	public List<CurrencyAccountDetails> currencyAccountBalanceDetails(List<ClientAccount> allAccounts) {
		List<ClientAccount> currencyAccounts = allAccounts.stream().filter(
				account -> account.getAccountTypeCode().getAccountTypeCode().equalsIgnoreCase(CURRENCY_ACCOUNT_CODE))
				.collect(Collectors.toList());

		List<CurrencyAccountDetails> currencyAccDetails = new ArrayList<>();
		for (ClientAccount eachClAcnt : currencyAccounts) {
			currencyAccDetails.add(mapToClientAcc(eachClAcnt));
		}
		currencyAccDetails.sort(byCurrBalance);
		return currencyAccDetails;
	}

	public CurrencyAccountDetails mapToClientAcc(ClientAccount clientAccount) {

		CurrencyConversionrate currencyConvRate = bankController
				.getCurrencyConversionsById(clientAccount.getCurrencyCode().getCurrencyCode());
		Integer decimalPlace = currencyConvRate.getCurrencyCode().getDecimalPlace();

		CurrencyAccountDetails currencyAccountDetails = new CurrencyAccountDetails();
		currencyAccountDetails.setAccountNumber(
				clientAccount.getClientAccountNumber() != null ? clientAccount.getClientAccountNumber() : EMPTY_VAL);
		currencyAccountDetails.setCurrency(clientAccount.getCurrencyCode().getCurrencyCode() != null
				? clientAccount.getCurrencyCode().getCurrencyCode()
				: EMPTY_VAL);
		currencyAccountDetails.setCurrencyBalance(
				clientAccount.getDisplayBalance() != null ? clientAccount.getDisplayBalance() : EMPTY_BAL);
		currencyAccountDetails.setCurrencyConvRate(currencyConvRate.getDecimal());

		double zarBal = 0.00;

		if (currencyConvRate.getConversionIndicator().equals("/")) {
			zarBal = clientAccount.getDisplayBalance() / currencyConvRate.getDecimal();
		} else if (currencyConvRate.getConversionIndicator().equals("*")) {
			zarBal = clientAccount.getDisplayBalance() * currencyConvRate.getDecimal();
		}

		NumberFormat numFormat = NumberFormat.getInstance();
		numFormat.setMaximumFractionDigits(decimalPlace);

		currencyAccountDetails.setZarAmount(Double.parseDouble(numFormat.format(zarBal).replaceAll(",", "")));

		return currencyAccountDetails;

	}

}
