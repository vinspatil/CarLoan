package com.DreamCar.app.report.serviceI;

import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import com.DreamCar.app.report.Dto.Customer;
import com.DreamCar.app.report.Dto.MailSender;



public interface CustomerService {
	public Integer saveData(Customer c);
	public void ganaratePdf(HttpServletResponse s,Integer cusId) throws Exception;
	public void ganarateExl(HttpServletResponse s,Integer cusId) throws Exception;
	public void sendMailWithAttachment(MailSender m);
	public void updateStatus(Integer id, LocalDate date);
	public String findDefaulterCustomer(Integer id);
	public String findCustomer(Integer id);
	
}

