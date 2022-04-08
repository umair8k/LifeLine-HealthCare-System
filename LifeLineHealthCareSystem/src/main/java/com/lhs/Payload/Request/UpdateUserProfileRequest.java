package com.lhs.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileRequest {
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String phoneNo;

}
