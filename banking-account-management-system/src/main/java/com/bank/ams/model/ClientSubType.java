package com.bank.ams.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CLIENT_SUB_TYPE")
public class ClientSubType {

	@Id
	@Column(name = "CLIENT_SUB_TYPE_CODE")
	private String clientSubTypeCode;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "CLIENT_TYPE_CODE")
	private ClientType clientTypeCode;

	@Column(name = "DESCRIPTION")
	private String clientTypeDescription;

}
