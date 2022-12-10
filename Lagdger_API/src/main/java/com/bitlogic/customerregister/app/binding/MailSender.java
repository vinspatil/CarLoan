package com.bitlogic.customerregister.app.binding;

import lombok.Data;

@Data
public class MailSender {
	private String toEmail;
	private String fromEmail;
	private String subject;
	private String txtmsg;
}

