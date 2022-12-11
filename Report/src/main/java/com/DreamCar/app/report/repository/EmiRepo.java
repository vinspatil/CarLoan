package com.DreamCar.app.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DreamCar.app.report.model.EmiCalculation;

@Repository
public interface EmiRepo extends JpaRepository<EmiCalculation, Integer> {
	public List<EmiCalculation> findAllByCustomerId(Integer id);
}
