package com.lhs.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordSmsRequest {
	
	private String phoneNo;
	private String message;
	private String newPassword;

}
