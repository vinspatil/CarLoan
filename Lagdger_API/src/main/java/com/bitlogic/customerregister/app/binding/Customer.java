package com.bitlogic.customerregister.app.binding;



import lombok.Data;
@Data
public class Customer {
	private Integer id;
	private String name;
	private Double loanAmount;
	private Double rateOfInterast;
	private Integer timePeriod;
}
