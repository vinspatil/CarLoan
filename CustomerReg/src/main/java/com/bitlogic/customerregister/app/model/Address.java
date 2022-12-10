package com.bitlogic.customerregister.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
	@TableGenerator(name = "id_generator", table = "id_gen_Addr", pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="task_gen", initialValue=1100)
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
	private Integer permanentAddressId;
	private Integer houseNumber;
	private String streetName;
	private String areaname;
	private String cityname;
	private String district;
	private String state;
	private Long pincode;
	
}
