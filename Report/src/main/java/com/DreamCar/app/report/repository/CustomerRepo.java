package com.bitlogic.customerregister.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitlogic.customerregister.app.model.CustomerDetail;
@Repository
public interface CustomerRepo extends JpaRepository<CustomerDetail, Integer> {

}
