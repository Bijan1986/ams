package com.bank.ams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CURRENCY")
public class Currency {

	@Id
	@Column(name = "CURRENCY_CODE")
	private String currencyCode;
	@Column(name = "DECIMAL_PLACES")
	private Integer decimalPlace;
	@Column(name = "DESCRIPTION")
	private String description;

}
