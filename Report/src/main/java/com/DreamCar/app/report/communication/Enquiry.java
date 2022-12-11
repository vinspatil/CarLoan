package com.DreamCar.app.report.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CUSTOMER-ENQUIRY")
public interface Enquiry {
	
	@GetMapping("/enquiry/statusChange/{id}")
	public void statusChange(@PathVariable int id);
}
