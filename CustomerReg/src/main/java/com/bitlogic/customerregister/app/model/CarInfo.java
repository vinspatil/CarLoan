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
public class CarInfo {
	@TableGenerator(name = "id_generator", table = "id_gen_carInfo", pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="task_gen", initialValue=1200)
        @Id
      @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
	private Integer carId;
	private Integer carModelNo;
	private String carName;
	private String brandName;
	private Double carPrice;
	private String colour;
	
}
