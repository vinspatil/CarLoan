package com.DreamCar.app.reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerRegApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRegApplication.class, args);
	}

}
