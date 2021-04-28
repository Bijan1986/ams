package com.bank.ams.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyAccountDetails {
	private String accountNumber;
	private String currency;
	private Double currencyBalance;
	private Double currencyConvRate;
	private Double zarAmount;
	
}
