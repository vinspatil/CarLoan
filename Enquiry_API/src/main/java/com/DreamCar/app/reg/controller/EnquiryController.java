package com.DreamCar.app.reg.controller;

import java.util.List;

import org.hibernate.loader.custom.CustomQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.DreamCar.app.reg.model.CustomerEnquiry;
import com.DreamCar.app.reg.service.EnquiryServiceInterface;


@RestController
@CrossOrigin
@RequestMapping("/enquiry")
public class EnquiryController {

	@Autowired
	EnquiryServiceInterface esi;
	
	@PostMapping(value="/postEnquiry")
	public String postEnquiry(@RequestBody CustomerEnquiry ce)
	{
		esi.postEnquiry(ce);
		return "Success";
	}
	
	@GetMapping(value="/getEnquiryList")
	public List<CustomerEnquiry> getEnquiry()
	{		
		List<CustomerEnquiry> list=esi.getEnquiry();
		return list;
	}
	
	@DeleteMapping(value="/deleteEnquiry/{custId}")
	public String deleteEnquiry( @PathVariable("custId") Integer id)
	{	esi.deleteEnquiry(id);
		return "deleted";
	}
	
	@PutMapping(value="/updateEnquiry/{custId}")
	public CustomerEnquiry updateEnquiry(@RequestBody CustomerEnquiry ce,@PathVariable("custId") Integer id)
	{
		return esi.updateEnquiry(ce,id);
	}
	
	@GetMapping("/getCibil/{id}")
	public Integer getcibilScore(@PathVariable int id) {
		CustomerEnquiry eq=esi.Enquiry(id);
		
		String endpointURL="http://localhost:3333/creditscore";
		
		RestTemplate rt=new RestTemplate();
		ResponseEntity<Integer> cib = rt.getForEntity(endpointURL, Integer.class);
		Integer cibil = cib.getBody();	
		eq.setCibil(cibil);
		if(cibil>700) {
			eq.setStatus("eligible");
		}
		else {
			eq.setStatus("lowCibil");
		}
		esi.postEnquiry(eq);
		
		return cibil;
	}
}
