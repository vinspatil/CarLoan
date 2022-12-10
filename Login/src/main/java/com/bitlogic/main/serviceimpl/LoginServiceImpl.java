package com.bitlogic.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlogic.main.exception.IdNotFoundException;
import com.bitlogic.main.model.LoginEntity;
import com.bitlogic.main.repositoty.LoginRepository;
import com.bitlogic.main.servicei.LoginServiceI;

@Service
public class LoginServiceImpl implements LoginServiceI{

	@Autowired
	LoginRepository lr;

	@Override
	public void saveLogin(LoginEntity login) {

		lr.save(login);
	}

	@Override
	public LoginEntity updateLogin(Integer id, LoginEntity l) {
		
		Optional<LoginEntity> op = lr.findById(id);
		
		if(op.isPresent())
		{
			LoginEntity L = op.get();
			L.setName(l.getName());
			L.setUsername(l.getUsername());
			L.setPassword(l.getPassword());
			L.setRole(l.getRole());
			L.setEmail(l.getEmail());
			return lr.save(L);
		}
		else
		{
			throw new IdNotFoundException("No such id = "+id+" found");
		}
		
	}

	@Override
	public void deleteLogin(Integer id) {
		lr.deleteById(id);
	}

	@Override
	public List<LoginEntity> getAllData() {

		return lr.findAll();
		
	}

	@Override
	public LoginEntity getData(Integer id) {
		
	Optional<LoginEntity> op = lr.findById(id);
	if(op.isPresent())
	{

		return op.get();
	}
	else 
	{
		throw new IdNotFoundException("No such id = "+id+" found");
	}
		
	}

	@Override
	public LoginEntity getData(String un, String ps) {

		
		return lr.findByUsernameAndPassword(un,ps);
		
		
	}
	
	
}
