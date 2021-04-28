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
@Entity(name = "ATM")
public class Atm {
	@Id
	@Column(name = "ATM_ID")
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "LOCATION")
	private String location;

}
