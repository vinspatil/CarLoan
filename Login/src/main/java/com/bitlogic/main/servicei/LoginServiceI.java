package com.bitlogic.main.servicei;



import java.util.List;

import com.bitlogic.main.model.LoginEntity;

public interface LoginServiceI {

	public void saveLogin(LoginEntity login);

	public LoginEntity updateLogin(Integer id, LoginEntity l);

	public void deleteLogin(Integer id);

	public List<LoginEntity> getAllData();

	public LoginEntity getData(Integer id);

	public LoginEntity getData(String un, String ps);


}
