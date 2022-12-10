package com.bitlogic.customerregister.app.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitlogic.customerregister.app.binding.SummeryBinding;
import com.bitlogic.customerregister.app.model.AccountDetail;
import com.bitlogic.customerregister.app.model.Address;
import com.bitlogic.customerregister.app.model.Document;
import com.bitlogic.customerregister.app.model.Customer;
import com.bitlogic.customerregister.app.model.SanctionLetter;
import com.bitlogic.customerregister.app.repository.CustomerDocumentRepo;
import com.bitlogic.customerregister.app.repository.CustomerRepo;
import com.bitlogic.customerregister.app.service.CustomerService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;


@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepo custoRepo;
	@Autowired
	CustomerDocumentRepo customerDocumentRepo;

	@Override
	public Integer saveCustomerDetail(Customer cus) {
		
		Customer save = custoRepo.save(cus);
//		
				return save.getCustomerId();
		
	}
	@Override
	public boolean saveCustomerDocument(Document cd) {
		System.out.println("jnsjdkjjn");
		Document save = customerDocumentRepo.save(cd);
		System.out.println("jnsjdkjjn");
		return false;
	}

	@Override
	public SummeryBinding findById(Integer id) {
//		CustomerSummaryBinding cs=new CustomerSummaryBinding();
//		Optional<CustomerRegistration> findById = customerRepo.findById(id);	
//		Optional<CustomerDocumentBinding> findById2 = customerDocumentRepo.findById(id);
//		if(findById.isPresent() && findById2.isPresent()) {
//			CustomerRegistration customerRegistration = findById.get();
//			cs.setCustomerRegister(customerRegistration);
//			CustomerDocumentBinding customerDocument = findById2.get();
//			cs.setCustomerDocument(customerDocument);
//			return cs;
//		}
		return null;
	}
	@Override
	public List<Customer> getAllCustomerDetail() {
		List<Customer> findAll = custoRepo.findAll();
//		
//		List<SummeryBinding> l=new ArrayList<>();
//		SummeryBinding cs= new SummeryBinding();
//		
//		for (Customer customerRegistration : findAll) {
//			
//			cs.setCustomerRegis(customerRegistration);
//			l.add(cs);
//		}
//		List<Document> findAll2 = customerDocumentRepo.findAll();
//		for (Document customerDocument : findAll2) {
//			
//			cs.setCustomerDocuments(customerDocument);
//			l.add(cs);
//		}
		return findAll;
	}
	@Override
	public Document getDocument(Integer id) {
		Document findByCustomerId = customerDocumentRepo.findByCustomerId(id);
		return findByCustomerId;
	}
	
	@Override
	public boolean updateSanctionLatter(Integer id, SanctionLetter sl) {
		Optional<Customer> cust = custoRepo.findById(id);
		if(cust!=null)
		{
			Customer Ap = cust.get();
			Integer sanctionId = Ap.getSanctionletter().getSanctionId();
			sl.setSanctionId(sanctionId);

			Ap.setSanctionletter(sl);
			custoRepo.save(Ap);
			
			return true;
		}
		
		return false;
	}

}
