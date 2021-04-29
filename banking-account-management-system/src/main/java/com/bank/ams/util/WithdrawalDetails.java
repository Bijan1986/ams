package com.bank.ams.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalDetails {
	private Integer atmAllocationId;
	private String clientAccountNumber;
	private Integer clientId;
	private Double amountToWithdraw;
	private double availableBalance;
	private Integer DenominationId;
	private String accountType;

}
