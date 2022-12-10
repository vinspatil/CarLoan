package com.bitlogic.customerregister.app.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitlogic.customerregister.app.model.Document;
@Repository
public interface CustomerDocumentRepo extends JpaRepository<Document, Integer> {
	public Document findByCustomerId(Integer id); 
}
