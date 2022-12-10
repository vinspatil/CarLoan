package com.DreamCar.app.cibil.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DreamCar.app.cibil.mapping.InterestCalculation;



@RestController
public class CibilController {
	
	@GetMapping("/creditscore")
	public ResponseEntity<Integer> getCreditScore(){
		int min = 600;  
		int max = 900;  
		Random r=new Random();
		int showMe = r.nextInt(min, max);	
		return new ResponseEntity<>(showMe, HttpStatus.OK);
	}
	@PostMapping("/getAmount")
	public ResponseEntity<Double> getAmount(@RequestBody InterestCalculation etc){
		double principal = (double)etc.getAmount();
		double rate=(double)7/1200;
				double time=(double)etc.getMonth();
		double emi=(principal*rate*Math.pow(1+rate,time))/(Math.pow(1+rate,time)-1);
		return new ResponseEntity<>(emi, HttpStatus.OK);
	}
	
}
