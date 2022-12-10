package com.DreamCar.app.reg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.DreamCar.app.reg.model.CustomerEnquiry;
@Service
public interface EnquiryServiceInterface {

	void postEnquiry(CustomerEnquiry ce);

	List<CustomerEnquiry> getEnquiry();

	void deleteEnquiry(Integer id);

	CustomerEnquiry updateEnquiry(CustomerEnquiry ce,Integer id);

	CustomerEnquiry Enquiry(Integer id);

}