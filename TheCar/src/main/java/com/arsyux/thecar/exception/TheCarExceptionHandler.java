package com.arsyux.thecar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.arsyux.thecar.dto.ResponseDTO;

@ControllerAdvice
@RestController
public class TheCarExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseDTO<String> globalExceptionHandler(Exception e) {
		return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

}
