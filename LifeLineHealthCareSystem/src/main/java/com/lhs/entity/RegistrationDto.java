package com.lhs.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegistrationDto {
	@NotEmpty(message = "firstname should be there")
	private String firstname;

	private String lastname;
	@NotEmpty(message = "username should be there")
	private String username;
	@Size(min = 8, message = "u must enter min 8 characters.....")
	private String password;

	private String dob;

	private String gender;
	private long mobileno;

}
