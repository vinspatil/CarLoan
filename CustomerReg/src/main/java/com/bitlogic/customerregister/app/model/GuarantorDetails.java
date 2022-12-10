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
public class GuarantorDetails {
	@TableGenerator(name = "id_generator", table = "id_gen_Gu_D", pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="task_gen", initialValue=1600)
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
	private Integer guarantorId;
	private String guarantorName;
	private Long guarantorMobileNo;
	private String guarantorPermanentAddress;

}
