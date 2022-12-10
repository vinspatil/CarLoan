package com.DreamCar.app.report.serviceI;

import java.util.Map;

import com.DreamCar.app.report.Dto.MailRequest;
import com.DreamCar.app.report.Dto.MailResponse;

import freemarker.template.Template;

public interface MailServiceInterface {

	MailResponse email(MailRequest request, Map<String, Object> model,Template t);



}
