package com.bitlogic.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoginEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer loginId;
	private String firstname;
	private String lastname;
	private String password;
	private String role;
	private String email;
	private String gender;
	private String birthday;
	@Lob
	private byte[] profile;

}
