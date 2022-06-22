package com.lhs.Payload.Request;

import com.lhs.Models.DoctorDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDoctorProfileRequest {

	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String phoneNo;
	private DoctorDetail doctorDetail;
}
