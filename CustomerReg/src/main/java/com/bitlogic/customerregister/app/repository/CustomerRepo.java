package com.bitlogic.customerregister.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitlogic.customerregister.app.model.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
