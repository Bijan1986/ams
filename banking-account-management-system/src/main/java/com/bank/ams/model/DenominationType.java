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
@Entity(name = "DENOMINATION_TYPE")
public class DenominationType {

	@Id
	@Column(name = "DENOMINATION_TYPE_CODE")
	private String code;

	@Column(name = "DESCRIPTION")
	private String description;

}
