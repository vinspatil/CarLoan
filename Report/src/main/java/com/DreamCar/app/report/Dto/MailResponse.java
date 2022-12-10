package com.DreamCar.app.report.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse {

	private String msg;
	private boolean status;
}
