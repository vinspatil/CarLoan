package com.bitlogic.customerregister.app.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitlogic.customerregister.app.binding.Customer;
import com.bitlogic.customerregister.app.binding.MailSender;
import com.bitlogic.customerregister.app.service.CustomerService;

import lombok.extern.slf4j.Slf4j;
import reactor.util.Loggers;
@Slf4j
@RestController
public class HomeController {
	
	@Value("${spring.mail.username}")
	private static String username;
	@Autowired
	CustomerService s;
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
		System.out.println("kjahbcjkahsc aschasiudasd aihciaushcasc ah cash");
		MailSender m=new MailSender();
		m.setFromEmail(username);
		m.setToEmail("prafull.sawake@gmail.com");
		m.setSubject("klsdkjd osjvisjv sojvshv sojvsidj sjisj ");
		m.setTxtmsg("sksmvksknv svmkmv svjks sv s");
		HomeController h=new HomeController();
		try {
			h.s.sendMailWithAttachment(m);
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
