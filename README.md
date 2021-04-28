# Account Management System business requirements

## 1. Display transactional account with balances

> i have added a client/customer search screen where we can put the client id .

![search customer](images/Capture1.JPG)

> client details screen 

![client details](images/Capture2.JPG)

> code snippet

```java

private Comparator<ClientAccount> byBalance = (cl1, cl2) -> cl2.getDisplayBalance()
			.compareTo(cl1.getDisplayBalance());

@GetMapping("/customer")
	public String showCustomer(@ModelAttribute("client") Client client, Model model) {
		Client selectedClient = bankController.getClientById(client.getClientId());
		model.addAttribute("selectedClient", selectedClient);
		List<ClientAccount> allAccounts = bankController.getAllClientAccountsById(selectedClient.getClientId());
		List<ClientAccount> trnscAccounts = allAccounts.stream()
				.filter(account -> account.getAccountTypeCode().getTransactional()).collect(Collectors.toList());
		trnscAccounts.sort(byBalance);
		model.addAttribute("accounts", trnscAccounts);
		return "customerDetails";
	}


```

## 2. Display currency account with converted rand value 

> for client id 1

![currency conversion details](images/Capture3.JPG)





