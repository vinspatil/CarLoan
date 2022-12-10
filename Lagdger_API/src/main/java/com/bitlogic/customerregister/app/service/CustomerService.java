package com.bitlogic.customerregister.app.service;

import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import com.bitlogic.customerregister.app.binding.Customer;
import com.bitlogic.customerregister.app.binding.MailSender;

public interface CustomerService {
	public Integer saveData(Customer c);
	public void ganaratePdf(HttpServletResponse s,Integer cusId) throws Exception;
	public void ganarateExl(HttpServletResponse s,Integer cusId) throws Exception;
	public void sendMailWithAttachment(MailSender m);
	public void updateStatus(Integer id, LocalDate date);
	public String findDefaulterCustomer(Integer id);
	public String findCustomer(Integer id);
	
}

