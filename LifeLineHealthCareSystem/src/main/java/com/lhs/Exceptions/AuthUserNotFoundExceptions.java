package com.lhs.Exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AuthUserNotFoundExceptions extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

}
