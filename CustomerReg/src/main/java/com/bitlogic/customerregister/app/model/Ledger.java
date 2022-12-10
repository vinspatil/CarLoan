package com.bitlogic.customerregister.app.model;

import java.time.LocalDate;


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
public class Ledger {
	@TableGenerator(name = "id_generator", table = "id_gen_Led_d", pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="task_gen", initialValue=1500)
        @Id
       @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
	private Integer ledgerId;	
	private String ledgerCreatedDate;
	private Double totalLoanAmount;
	private Double payableAmountwithInterest;
	private Integer tenure;
	private Double monthlyEmi;	
	private String amountPaidTillDate;	
	private Double reamainingAmount;	
	private String nextEmiStartDate;
	private String nextEmiEnddate;
	private Integer defaulterCount;
	private String previousEmiStatus;
	private String currentMonthEmiStatus;
	private String loanEndDate;
	private String loanStatus;
	
}
