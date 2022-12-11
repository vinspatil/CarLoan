package com.DreamCar.app.report.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DreamCar.app.report.Dto.Customer;
import com.DreamCar.app.report.Dto.MailRequest;
import com.DreamCar.app.report.Dto.MailResponse;
import com.DreamCar.app.report.Dto.MailSender;
import com.DreamCar.app.report.communication.Enquiry;
import com.DreamCar.app.report.serviceI.CustomerService;
import com.DreamCar.app.report.serviceI.MailServiceInterface;


import freemarker.template.Configuration;
import freemarker.template.Template;


@CrossOrigin("*")
@RestController
@RequestMapping("/report")
public class MailController {

	@Autowired
	CustomerService s;

	@Autowired
	MailServiceInterface msi;
	
	@Autowired
    private Configuration config;
	@Autowired
	private Enquiry eq;
	
	@PostMapping(value="/email")
	public MailResponse sendSanctionLetter(@RequestBody MailRequest request)
	{
		
		Template t=null;
		try {
			System.out.println(request.getAppId()+"-------------------------------------------------------------------------------------------------------------------------");
			if(request.getStatus().equals("lowCibil")) {
				eq.statusChange(request.getAppId());
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
	

	@PostMapping("/saveEmi")
	public ResponseEntity<Integer> saveData(@RequestBody Customer c) {
		Integer saveData = s.saveData(c);
		return new ResponseEntity<>(saveData, HttpStatus.CREATED);
	}
	
	@GetMapping("/exel/{id}")
	public void getExcl(HttpServletResponse responce,@PathVariable Integer id )throws Exception {
		responce.setContentType("application/octet-stream");
		String hk="Content-Disposition";
		String val="attachment;filename=data.xls";
		responce.setHeader(hk, val);
		s.ganarateExl(responce, id);
	}
	@GetMapping("/genPdf/{id}")
	public void getPdf(HttpServletResponse response,@PathVariable Integer id)throws Exception {
		response.setContentType("application/pdf");
		String customer = s.findCustomer(id);
		String hk="Content-Disposition";
		String val="attachment;filename="+customer+".pdf";
		
		response.setHeader(hk, val);
		s.ganaratePdf(response, id);
		main();
	}
	public static void main() {
		
		MailSender m=new MailSender();
		
		m.setToEmail("prafull.sawake@gmail.com");
		m.setSubject("klsdkjd osjvisjv sojvshv sojvsidj sjisj ");
		m.setTxtmsg("sksmvksknv svmkmv svjks sv s");
		//HomeController h=new HomeController();----------------------------------------
		try {
			
			//h.s.sendMailWithAttachment(m);-----------------------------------------------
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@PutMapping("/update/{date}/{id}")
	public ResponseEntity<String> updateStatus(@PathVariable String date,@PathVariable Integer id) {
		LocalDate parse = LocalDate.parse(date);
		s.updateStatus(id,parse);
		return new ResponseEntity<>("Status Update", HttpStatus.OK);
	}
	@GetMapping("/defa/{id}")
	public ResponseEntity<Boolean> findDefaulder(@PathVariable Integer id) {
		String a = s.findDefaulterCustomer(id);
		System.out.println("hwjbegdahd"+a);
		boolean msg;
		if(a=="") {
			msg=false;
		}else {
			msg=true;
		}
		
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
}