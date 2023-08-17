package com.arsyux.thecar.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// 스프링에서는 애플리케이션에서 예외가 발생했을 때, 예외 처리 핸들러로 예외를 일괄 처리할 수 있다.
// TheCarException을 비롯한 모든 종류의 예외를 처리하기 위한 예외 처리 핸들러를 작성한다.
@ControllerAdvice
@RestController
public class TheCarExceptionHandler {

	// globalExceptionHandler() 메소드는 애플리케이션에서 발생된 모든 예외를 받아서 HTML메시지로 반환한다.
	@ExceptionHandler(value = Exception.class)
	public String globalExceptionHandler(Exception e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
	
}
