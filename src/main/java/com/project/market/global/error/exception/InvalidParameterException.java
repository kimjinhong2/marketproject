package com.project.market.global.error.exception;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class InvalidParameterException extends IllegalArgumentException{

	public InvalidParameterException(String message) {
		super(message);
	}
	
	public static void throwErrorMessage(Errors errors) {
		StringBuilder sb = new StringBuilder();
		List<ObjectError> allErrors=errors.getAllErrors();
		
		for(ObjectError allError : allErrors) {
			sb.append(allError.getDefaultMessage() + "\n");
		}
		
		throw new InvalidParameterException(sb.toString());
	}
	
}
