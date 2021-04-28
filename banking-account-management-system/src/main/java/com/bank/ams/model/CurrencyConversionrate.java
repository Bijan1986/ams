package com.bank.ams.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CURRENCY_CONVERSION_RATE")
public class CurrencyConversionrate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5871245402548666460L;
	
	@Id
	private String currencyCodeId;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "CURRENCY_CODE")
	@MapsId
	private Currency currencyCode;
	@Column(name = "CONVERSION_INDICATOR")
	private String conversionIndicator;
	@Column(name = "RATE")
	private double decimal;

}
