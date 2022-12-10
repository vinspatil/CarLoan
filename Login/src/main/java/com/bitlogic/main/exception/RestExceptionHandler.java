package com.bitlogic.main.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ApiError> ProductNotFound(IdNotFoundException e,HttpServletRequest request)
	{
		ApiError ae = new ApiError();
		
		ae.setCode(HttpStatus.NOT_FOUND.value());
		ae.setError(HttpStatus.NOT_FOUND);
		ae.setMessage(e.getMessage());
		ae.setTimeDate(new Date());
		ae.setPath(request.getRequestURI());
		ae.setTrace(e.toString());
		
		return new ResponseEntity<ApiError>(ae,HttpStatus.NOT_FOUND);
	}

}
