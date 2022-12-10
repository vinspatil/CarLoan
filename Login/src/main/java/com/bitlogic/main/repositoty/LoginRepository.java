package com.bitlogic.main.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bitlogic.main.model.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Integer>{

	LoginEntity findByUsernameAndPassword(String un, String ps);

}
