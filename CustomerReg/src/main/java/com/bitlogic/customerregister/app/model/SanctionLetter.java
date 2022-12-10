package com.bitlogic.customerregister.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanctionLetter {
	@TableGenerator(name = "id_generator", table = "id_gen_San_L", pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="task_gen", initialValue=1300)
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")	private Integer sanctionId;
	private Integer loanNumber;
	private String agreementDate;
	private Double customerTotalLoanRequired;
	private String bankName;
	private Long accountNumber;
	private Double sanctionAmount;
	private String amountPaidDate;
}
