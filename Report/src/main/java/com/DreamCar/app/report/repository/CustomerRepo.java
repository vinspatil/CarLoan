package com.DreamCar.app.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DreamCar.app.report.model.CustomerDetail;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerDetail, Integer> {

}
