package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Data Not Found!")
public class BadRequestException extends Exception {
	private static final long serialVersionUID = 1L;

}
