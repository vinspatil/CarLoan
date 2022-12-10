package com.bitlogic.customerregister.app.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class EmiCalculation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentNumber ;
	private Integer customerId;
	private LocalDate paymentdate;
	private double balance;
	private double emiPaid;
	private String atatus;
}
