package com.arsyux.thecar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.arsyux.thecar.dto.ResponseDTO;

// 모든 종류의 예외를 처리하기 위한 예외 처리 핸들러를 작성
@ControllerAdvice
@RestController
public class TheCarExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseDTO<String> globalExceptionHandler(Exception e) {
		return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
	
}