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
@Entity(name = "DENOMINATION")
public class Denomination {
	@Id
	@Column(name = "DENOMINATION_ID")
	private Integer id;

	@Column(name = "VALUE")
	private Double value;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "DENOMINATION_TYPE_CODE")
	private DenominationType denominationType;

}
