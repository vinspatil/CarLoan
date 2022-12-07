package com.DreamCar.app.reg.serviceimpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DreamCar.app.reg.model.CustomerEnquiry;
import com.DreamCar.app.reg.repository.EnquiryRepository;
import com.DreamCar.app.reg.service.EnquiryServiceInterface;

@Service
public class EnquiryServiceImpl implements EnquiryServiceInterface{

	@Autowired
	EnquiryRepository er;

	@Override
	public void postEnquiry(CustomerEnquiry ce) {
		// TODO Auto-generated method stub
		er.save(ce);
	}

	@Override
	public List<CustomerEnquiry> getEnquiry() {
		
		return er.findAll();
	}

	@Override
	public void deleteEnquiry(Integer id) {
		// TODO Auto-generated method stub
		er.deleteById(id);
	}

	@Override
	public CustomerEnquiry updateEnquiry(CustomerEnquiry ce,Integer id) {
		// TODO Auto-generated method stub
		Optional<CustomerEnquiry> opr=er.findById(id);
		
		if(opr.isPresent())
		{
			   CustomerEnquiry cse=  opr.get();
			   cse.setFirstName(ce.getFirstName());
			   cse.setLastName(ce.getLastName());
			   cse.setEmailId(ce.getEmailId());
			   cse.setAge(ce.getAge());
			   cse.setMbNo(ce.getMbNo());
			   cse.setPancardNo(ce.getPancardNo());
			   cse.setStatus(ce.getStatus());
			   cse.setCibil(ce.getCibil());	 
			   
			   return er.save(cse);
			   
		}
		else
		{
			return null;
		}
	}
}
