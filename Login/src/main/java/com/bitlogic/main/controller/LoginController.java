package com.bitlogic.main.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bitlogic.main.model.LoginEntity;
import com.bitlogic.main.servicei.LoginServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;




@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginServiceI ls;
	
// Post API for registers

	@PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<LoginEntity> postLogin(@RequestParam("photo") MultipartFile photo, @RequestPart("name") String name,@RequestPart("username") String username,@RequestPart("email") String email,@RequestPart("password") String password,@RequestPart("role") String role )
	{
		LoginEntity login = new LoginEntity();
		try {
			ObjectMapper om = new ObjectMapper();
			
			login.setPhoto(photo.getBytes());
			
			LoginEntity L1 = om.readValue(name, LoginEntity.class);
			LoginEntity L2 = om.readValue(username, LoginEntity.class);
			LoginEntity L3 = om.readValue(password, LoginEntity.class);
			LoginEntity L4 = om.readValue(email, LoginEntity.class);
			LoginEntity L5 = om.readValue(role, LoginEntity.class);

			login.setName(L1.getName());
			login.setUsername(L2.getUsername());
			login.setPassword(L3.getPassword());
			login.setEmail(L4.getEmail());
			login.setRole(L5.getRole());
			
			ls.saveLogin(login);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<LoginEntity>(login,HttpStatus.CREATED);
	}
	

// ///////////////////////////////////////////////////////////////////////////////////////////////
// Put API for Update
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<LoginEntity> putLogin(@RequestParam("photo") MultipartFile photo, @RequestPart("name") String name,@RequestPart("username") String username,@RequestPart("email") String email,@RequestPart("password") String password,@RequestPart("role") String role, @PathVariable("id") Integer id)
	{
	
		LoginEntity L = new LoginEntity();
		
		try {
		ObjectMapper om = new ObjectMapper();		
		L.setPhoto(photo.getBytes());		
		LoginEntity L1 = om.readValue(name, LoginEntity.class);
		LoginEntity L2 = om.readValue(username, LoginEntity.class);
		LoginEntity L3 = om.readValue(password, LoginEntity.class);
		LoginEntity L4 = om.readValue(email, LoginEntity.class);
		LoginEntity L5 = om.readValue(role, LoginEntity.class);
		
		L.setName(L1.getName());
		L.setUsername(L2.getUsername());
		L.setPassword(L3.getPassword());
		L.setEmail(L4.getEmail());
		L.setRole(L5.getRole());
		
		ls.updateLogin(id,L);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		return new ResponseEntity<LoginEntity>(L,HttpStatus.OK);
	}
	
// ///////////////////////////////////////////////////////////////////////////////////////////////	
// Delete API for Delete
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<LoginEntity> deleteLogin(@PathVariable Integer id)
	{
		ls.deleteLogin(id);
		return new ResponseEntity<LoginEntity>(HttpStatus.NO_CONTENT);
	}
	
// ///////////////////////////////////////////////////////////////////////////////////////////////	
// Get API for getting all data
	
	@RequestMapping("/getAllData")
	public ResponseEntity<List<LoginEntity>> getLogin()
	{
		List<LoginEntity> list = ls.getAllData();
		
		return new ResponseEntity<List<LoginEntity>>(list,HttpStatus.OK);
		
	}

// ///////////////////////////////////////////////////////////////////////////////////////////////
// Get API for getting single data by ID
	
	@GetMapping(value = "/getSingleData/{id}")
	public ResponseEntity<LoginEntity> getLogin(@PathVariable("id") Integer id )
	{
		LoginEntity data = ls.getData(id);
		
		return new ResponseEntity<LoginEntity>(data,HttpStatus.OK);
	}
	
// ///////////////////////////////////////////////////////////////////////////////////////////////
// Get API for getting single data by username and password
	
	@GetMapping(value = "/getSingleData/{username}/{password}")
	public ResponseEntity<LoginEntity> getLogin(@PathVariable("username") String un, @PathVariable("password") String ps)
	{
		
		LoginEntity l = ls.getData(un,ps);
		
		return new ResponseEntity<LoginEntity>(l,HttpStatus.OK);
	}
	
	
	
	
}
