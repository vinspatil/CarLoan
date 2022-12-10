package com.bitlogic.customerregister.app.service;

import java.util.List;

import com.bitlogic.customerregister.app.binding.SummeryBinding;
import com.bitlogic.customerregister.app.model.Document;
import com.bitlogic.customerregister.app.model.Customer;
import com.bitlogic.customerregister.app.model.SanctionLetter;


public interface CustomerService {
	public Integer saveCustomerDetail(Customer cus);
	public boolean saveCustomerDocument(Document cd);
//	public List<SummeryBinding> getAllCustomerDetail();
	public List<Customer> getAllCustomerDetail();
	public SummeryBinding findById(Integer id);
	public Document getDocument(Integer id);
	public boolean updateSanctionLatter(Integer id, SanctionLetter sl);
}
