package com.bitlogic.main.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
	
	private Integer code;
	private String message;
	private Date timeDate;
	private String path;
	private HttpStatus error;
	private String trace;

}
