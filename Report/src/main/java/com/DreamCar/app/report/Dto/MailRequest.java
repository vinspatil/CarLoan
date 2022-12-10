package com.DreamCar.app.report.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
	private Integer appId;
    private String name;
	private String to;
	private String status;
	private String subject;
	
}
