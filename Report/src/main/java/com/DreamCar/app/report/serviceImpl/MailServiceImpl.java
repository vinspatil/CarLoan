package com.DreamCar.app.report.serviceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.DreamCar.app.report.Dto.MailRequest;
import com.DreamCar.app.report.Dto.MailResponse;
import com.DreamCar.app.report.config.ApiConfig;
import com.DreamCar.app.report.serviceI.MailServiceInterface;

import freemarker.template.Template;
import freemarker.template.*;
@Service
public class MailServiceImpl implements MailServiceInterface {
	  
	@Autowired
	private JavaMailSender sender;
	
	
	
	@Override
	public MailResponse email(MailRequest request,Map<String,Object> model,Template t)
	{
		MailResponse response=new MailResponse();
		
		MimeMessage message=sender.createMimeMessage();
		try
		{
		MimeMessageHelper helper=new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			
			helper.addAttachment("", new ClassPathResource(""));
			
			
			String html=FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			helper.setTo(request.getTo());
			helper.setText(html, true);
			helper.setSubject(request.getSubject());
			//helper.setFrom(request.getFrom()); 			
			sender.send(message);
			response.setMsg("mail send to"+request.getTo());			
			response.setStatus(Boolean.TRUE);			
		}
		catch(MessagingException | IOException |TemplateException e)
		{
			response.setMsg("Mail sending Failure..."+e.getMessage());
             response.setStatus(Boolean.FALSE);
		}
		
		return response;
	}

}
