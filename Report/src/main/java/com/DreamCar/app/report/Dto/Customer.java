package com.DreamCar.app.report.Dto;



import lombok.Data;
@Data
public class Customer {
	private Integer id;
	private String name;
	private Double loanAmount;
	private Double rateOfInterast;
	private Integer timePeriod;
}
