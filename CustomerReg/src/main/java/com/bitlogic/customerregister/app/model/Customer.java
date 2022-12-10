package com.bitlogic.customerregister.app.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@TableGenerator(name = "id_generator", table = "id_gen_Cus_Reg", pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="task_gen", initialValue=1100)
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
	private Integer customerId;
	private String customerName;
	private String customerDateOfBirth;
	private Integer customerAge;
	private String customerGender;
	private String customerEmail;
	private Double customerTotalLoanRequired;
	private String status;
	private Integer cibilscore;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
	private Address address;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
	private Profession profession;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
	private AccountDetail accountdetails;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
	private GuarantorDetails gurantordetails;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
	private Ledger ledger;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
	private SanctionLetter sanctionletter;
	@OneToOne(cascade = CascadeType.ALL)
	private CarInfo carinfo;
	
}
