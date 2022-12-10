package com.DreamCar.app.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Cibil1Application {

	public static void main(String[] args) {
		SpringApplication.run(Cibil1Application.class, args);
	}

}
