package com.bank.ams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Bijan Nayak enitiy table for CLIENT_TYPE
 *
 */
@Data
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CLIENT_TYPE")
public class ClientType {

	@Id
	@Column(name = "CLIENT_TYPE_CODE")
	private String clientTypeCode;

	@Column(name = "DESCRIPTION")
	private String clientTypeDescription;

}
