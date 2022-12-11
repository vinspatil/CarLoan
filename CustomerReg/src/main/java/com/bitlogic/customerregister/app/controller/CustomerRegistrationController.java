package com.bitlogic.customerregister.app.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bitlogic.customerregister.app.binding.SummeryBinding;
import com.bitlogic.customerregister.app.model.Document;
import com.bitlogic.customerregister.app.model.Customer;
import com.bitlogic.customerregister.app.model.SanctionLetter;
import com.bitlogic.customerregister.app.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.CustomLog;
@CrossOrigin("*")
@RestController
@RequestMapping("/reg")
public class CustomerRegistrationController {
	@Autowired
	CustomerService custo;
	@PostMapping(value = "/saveData")	
	public ResponseEntity<Integer> saveCustomerDetails(@RequestBody Customer cus){
		System.out.println(cus);
		Integer saveCustomerDetail = custo.saveCustomerDetail(cus);
		return new ResponseEntity<>(saveCustomerDetail, HttpStatus.CREATED);
	}
//	@GetMapping("/getData")
//	public ResponseEntity<List<SummeryBinding>> getDate(){
//		 List<SummeryBinding> allCustomerDetail = custo.getAllCustomerDetail();
//		return new ResponseEntity<>(allCustomerDetail, HttpStatus.OK);
//	}
	@GetMapping("/getData")
	public ResponseEntity<List<Customer>> getDate(){
		 List<Customer> allCustomerDetail = custo.getAllCustomerDetail();
		return new ResponseEntity<>(allCustomerDetail, HttpStatus.OK);
	}
	
	
	@PutMapping("/updateData")
	public ResponseEntity<SummeryBinding> updateData(@RequestBody SummeryBinding s){
		return new ResponseEntity<>(null, null);
	}
	@PostMapping(value = "/saveDoc",consumes = MediaType.MULTIPART_FORM_DATA )
	public String saveDocument(@RequestParam("identityProof") MultipartFile file, 
			@RequestParam("addressProof") MultipartFile file1,@RequestParam("incomeProof1") MultipartFile file2,
			@RequestParam("incomeProof2") MultipartFile file3,@RequestParam("incomeProof3") MultipartFile file4,
			@RequestPart("customerId") String customerId) {
		
		try {
			ObjectMapper om=new ObjectMapper();
			Document c=new Document();
			
			c.setCustomerId(om.readValue(customerId, Document.class).getCustomerId());
			//c.setDocumentId(om.readValue(documentId, CustomerDocument.class).getDocumentId());
			c.setAddressProof(file1.getBytes());
			c.setIdentityProof(file.getBytes());
			c.setIncomeProof1(file2.getBytes());
			c.setIncomeProof2(file3.getBytes());
			c.setIncomeProof3(file4.getBytes());
			System.out.println("jnsjdkjjn");
			boolean saveCustomerDocument = custo.saveCustomerDocument(c);
			System.out.println("jnsjdkjjn");
			if(saveCustomerDocument)
				return "Document Added";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getDoc/{id}")
	public ResponseEntity<Document> getDocumentByCusId(@PathVariable Integer id) {
		Document document = custo.getDocument(id);
		return new ResponseEntity<>(document, HttpStatus.OK);
				
	}
	
	@GetMapping("/getSanction/{id}")
	public ResponseEntity<SanctionLetter> getSanctionLetter(@PathVariable Integer id) {
		SanctionLetter sanction = custo.getSanction(id);
		return new ResponseEntity<>(sanction, HttpStatus.OK);
				
	}
	@PutMapping(value = "/savesanctionLatter/{id}")
	public String save(@PathVariable Integer id, @RequestBody SanctionLetter sl)
	{
		 boolean isUpdate = custo.updateSanctionLatter(id,sl);
		 if(isUpdate==true)
		 {
			 return "Success";
		 }
		 else
		 {
			 return "Operation Fail";
		 }
		 
	}
	
}
