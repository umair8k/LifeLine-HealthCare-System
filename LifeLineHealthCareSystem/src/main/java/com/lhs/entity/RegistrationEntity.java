package com.lhs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class RegistrationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstname;

	private String lastname;
	private String username;
	private String password;


	private String dob;

	private String gender;

	private long mobileno;

}
