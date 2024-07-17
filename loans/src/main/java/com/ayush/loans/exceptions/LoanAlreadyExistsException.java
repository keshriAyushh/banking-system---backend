package com.ayush.loans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends RuntimeException {
	public LoanAlreadyExistsException(String message) {
		super(message);
	}
}
