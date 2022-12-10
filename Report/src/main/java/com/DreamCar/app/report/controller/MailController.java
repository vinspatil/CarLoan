package com.DreamCar.app.report.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DreamCar.app.report.Dto.MailRequest;
import com.DreamCar.app.report.Dto.MailResponse;
import com.DreamCar.app.report.serviceI.MailServiceInterface;

import freemarker.template.Configuration;
import freemarker.template.Template;


@CrossOrigin("*")
@RestController
@RequestMapping("/report")
public class MailController {

	@Autowired
	MailServiceInterface msi;
	
	@Autowired
    private Configuration config;
	
	@PostMapping(value="/email")
	public MailResponse sendSanctionLetter(@RequestBody MailRequest request)
	{
		Template t=null;
		try {
			if(request.getStatus().equals("lowCibil")) {
				request.setSubject("LETTER OF REJECTION FOR LOAN REQUEST");
				t=config.getTemplate("emailreject.template.ftl");
			}
			if(request.getStatus().equals("eligible")) {
				request.setSubject("LETTER OF ELIGIBLE FOR LOAN REQUEST");
				t=config.getTemplate("emailregister.template.ftl");
			}			
			if(request.getStatus().equals("sanction")) {
				request.setSubject("LETTER OF SANCTION FOR LOAN REQUEST");
				t=config.getTemplate("emailloanapproved.template.ftl");
			}	
			if(request.getStatus().equals("verifyfailed")) {
				request.setSubject("LETTER OF REJECTION OF LOAN REQUEST");
				t=config.getTemplate("emailverifyfailed.template.ftl");
			}
			if(request.getStatus().equals("disbursement")) {
				request.setSubject("LETTER  OF DISBURSEMENT FOR LOAN REQUEST");
				t=config.getTemplate("emaildisbursement.template.ftl");
			}
		
		Map<String,Object> model=new HashMap<>();
		model.put("name",request.getName());
		model.put("location","Pune");
		return msi.email(request,model,t);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	
}