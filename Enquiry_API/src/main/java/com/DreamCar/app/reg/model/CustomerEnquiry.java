package com.DreamCar.app.reg.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerEnquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer custId;
	private String firstName;
	private String lastName;
	private Long mbNo;
	private String emailId;
	private Integer age;
	private Long panCardNo;

}
