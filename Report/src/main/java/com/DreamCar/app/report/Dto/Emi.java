package com.bitlogic.customerregister.app.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class Emi {
	private Integer paymentNumber;
	private LocalDate paymentdate;
	private double balance;
	private double emiPaid;
	private String atatus;
}
