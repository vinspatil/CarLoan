package com.DreamCar.app.report.Dto;

import lombok.Data;

@Data
public class MailSender {
	private String toEmail;
	private String fromEmail;
	private String subject;
	private String txtmsg;
}

