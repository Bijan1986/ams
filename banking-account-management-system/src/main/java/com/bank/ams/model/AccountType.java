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
@Entity(name = "ACCOUNT_TYPE")
public class AccountType {

	@Id
	@Column(name = "ACCOUNT_TYPE_CODE")
	private String accountTypeCode;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "TRANSACTIONAL")
	private Boolean transactional;

}
