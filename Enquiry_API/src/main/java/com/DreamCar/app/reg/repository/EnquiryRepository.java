package com.DreamCar.app.reg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.DreamCar.app.reg.model.CustomerEnquiry;



@Repository
public interface EnquiryRepository extends JpaRepository<CustomerEnquiry, Integer>{

	
}
