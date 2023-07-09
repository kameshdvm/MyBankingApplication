package com.mybank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> commonException(Exception e, WebRequest request)
	{
		
		e.getMessage();
		return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public  ResponseEntity<Object> nullPoineterEx(NullPointerException e)
	{
		e.getMessage();
		e.getCause();
		e.printStackTrace();
		return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
}