package com.bank.ams.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bank.ams.model.Atm;
import com.bank.ams.model.AtmAllocation;
import com.bank.ams.model.Client;
import com.bank.ams.model.ClientAccount;
import com.bank.ams.model.CurrencyConversionrate;
import com.bank.ams.service.AtmAllocationService;
import com.bank.ams.service.ClientAccountService;
import com.bank.ams.service.ClientService;
import com.bank.ams.util.CurrencyAccountDetails;
import com.bank.ams.util.WithdrawalDetails;

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
	
	List<ClientAccount> trnscAccounts = new ArrayList<>();
	List<ClientAccount> allAccounts = new ArrayList<>();

	// private Function<ClientAccount, CurrencyAccountDetails> currencyFunc =
	// (t,r)-> r.set ;

	@Autowired
	private BankController bankController;
	
	@Autowired
	private AtmAllocationService atmAllocationService;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientAccountService clientAccountService;

	@GetMapping("/")
	public String searchCustomer(Model theModel) {
		theModel.addAttribute("client", new Client());
		return "index";
	}

	@GetMapping("/customer")
	public String showCustomer(@ModelAttribute("client") Client client, Model model) {
		Client selectedClient = clientService.getClientById(client.getId()).get();
		model.addAttribute("selectedClient", selectedClient);
		allAccounts = clientAccountService.getAllClientAccountByClientId(selectedClient);
		trnscAccounts = allAccounts.stream()
				.filter(account -> account.getAccountTypeCode().getTransactional()).collect(Collectors.toList());
		trnscAccounts.sort(byBalance);
		model.addAttribute("accounts", trnscAccounts);
		model.addAttribute("clientId", client.getId());
		model.addAttribute("currencyAccounts", currencyAccountBalanceDetails(allAccounts));
		model.addAttribute("allAtms", bankController.getAllAtmAllocations());
		return "customerDetails";
	}
	
	@GetMapping("/clients/withdraw/{clientId}/{clientAccountNumber}")
	public String getWithdraw(@PathVariable("clientId")Integer id, @PathVariable("clientAccountNumber")String clientAccountNumber,Model model) {
		List<Atm> allAtms = bankController.getAllAtms();
		WithdrawalDetails withdrawalDetails = new WithdrawalDetails();
		withdrawalDetails.setClientId(id);
		withdrawalDetails.setClientAccountNumber(clientAccountNumber);
		for(ClientAccount eachCa: trnscAccounts) {
			if(eachCa.getClientAccountNumber().equals(clientAccountNumber)) {
				withdrawalDetails.setAccountType(eachCa.getAccountTypeCode().getDescription());
				withdrawalDetails.setAvailableBalance(eachCa.getDisplayBalance());
			}
		}
		
		model.addAttribute("withDrawalDetails", withdrawalDetails);
		model.addAttribute("atms", allAtms);
		return "withdrawCash";
	}
	
	@PostMapping("/withdraw/cash")
	public String withdrawMoney(@ModelAttribute("withDrawalDetails")WithdrawalDetails wd, Model model, RedirectAttributes redirectAttrs) {
		double amountTwd = wd.getAmountToWithdraw();
		Integer clientId = wd.getClientId();
		String accNumb = wd.getClientAccountNumber();
		Integer atmId = wd.getAtmAllocationId();
		ClientAccount clientAccount = clientAccountService.getClientAccountByClientIdAndAccNumb(accNumb);
		Double avlBalAcc = clientAccount.getDisplayBalance();
		List<AtmAllocation> atmAllocations = bankController.getAllAtmAllocations();
		List<AtmAllocation> selectedAtmAllocations = atmAllocations.stream().filter(t->t.getAtmId().getId()==atmId).collect(Collectors.toList());
		double availableFundsinAtm = calculateAmount(selectedAtmAllocations);
		if(availableFundsinAtm >= amountTwd) {
			withdrawcash(selectedAtmAllocations, availableFundsinAtm, amountTwd);
			Double amntLftAcc = avlBalAcc - amountTwd;
			clientAccount.setDisplayBalance(amntLftAcc);
			clientAccountService.saveClientAccount(clientAccount);
		}
		
		redirectAttrs.addAttribute("id", clientId);
		return "redirect:/customer";
	}

	private void withdrawcash(List<AtmAllocation> selectedAtmAllocations, double availableFundsinAtm,
			double amountTwd) {
		double amountTowithDraw = amountTwd;
		Map<Integer, AtmAllocation> atmTobeAllocatedMap = new HashMap<>();
		for (AtmAllocation eachAlloc : selectedAtmAllocations) {
			double totalPriceInAllocation = (eachAlloc.getCount()) * ((eachAlloc.getDenominationId().getValue()));
			
			Integer coinOrNotesLeft = 0;
			if (amountTowithDraw != 0.00) {
				Integer coinOrNoteToBeUsed = (int) ((amountTowithDraw) / (eachAlloc.getDenominationId().getValue()));
				coinOrNotesLeft= (eachAlloc.getCount()) - coinOrNoteToBeUsed;
				if(coinOrNotesLeft >= 0) {
					Integer remainder = (int) ((amountTowithDraw) % (eachAlloc.getDenominationId().getValue()));
					if (remainder == 0) {
						amountTowithDraw = 0.00;
					} else {
						amountTowithDraw = remainder;
					}
				}else {
					amountTowithDraw -= totalPriceInAllocation;
					coinOrNotesLeft = 0;
				}
				
				atmTobeAllocatedMap.put(eachAlloc.getId(), updateAtmAllocation(eachAlloc, coinOrNotesLeft));
			}

		}

	}

	private AtmAllocation updateAtmAllocation(AtmAllocation eachAlloc, Integer coinOrNoteLeft) {
		AtmAllocation atmAllocation = eachAlloc;
		atmAllocation.setCount(coinOrNoteLeft);
		atmAllocationService.saveAtmAllocation(atmAllocation);
		return atmAllocation;
		
		
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
	
	public double calculateAmount(List<AtmAllocation> atmAllocations) {
		
		double availableFunds = 0.00;
		for(AtmAllocation eachAtmAlcn:atmAllocations) {
			double total = (eachAtmAlcn.getDenominationId().getValue()) * (eachAtmAlcn.getCount());
			availableFunds += total;
		}
		
		return availableFunds;
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
