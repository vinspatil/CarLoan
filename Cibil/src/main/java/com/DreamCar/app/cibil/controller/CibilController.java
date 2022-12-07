package com.DreamCar.app.cibil.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DreamCar.app.cibil.mapping.InterestCalculation;

@RestController
public class CibilController {
	
	@PostMapping("/creditscore/{panNO}")
	public ResponseEntity<Integer> getCreditScore(@PathVariable String panNO){
		int min = 600;  
		int max = 900;  
		Random r=new Random();
		int showMe = r.nextInt(min, max);
		//Generate random double value from 200 to 400   
		//System.out.println("Random value of type double between "+min+" to "+max+ ":");  
		double a = Math.random()*(max-min+1)+min;   
		System.out.println(a);  
		int aa=(int)a;
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
