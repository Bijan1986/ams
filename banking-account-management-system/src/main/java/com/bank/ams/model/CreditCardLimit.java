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
@Entity(name = "CREDIT_CARD_LIMIT")
public class CreditCardLimit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2719856959203233280L;

	@Id
	private String clientAccountNumberId;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "CLIENT_ACCOUNT_NUMBER")
	@MapsId
	private ClientAccount clientAccountNumber;

	@Column(name = "ACCOUNT_LIMIT")
	private Double accountLimit;

}
