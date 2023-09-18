package com.arsyux.thecar.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.arsyux.thecar.dto.ResponseDTO;

@Component
@Aspect
public class ValidationCheckAdvice {

	// AOP를 이용한 유효성 검사
	// UserController 클래스와 PostController 클래스에서 반복하여 유효성 검사 로직을 사용했다.
	// 지금은 회원가입과 포스트 등록 화면에서만 유효성을 확인하고 있는데, 이후 다른 화면에도 유효성 검사 기능을 추가해야 한다면
	// 당연히 중복되는 코드가 더 많아질 것이다.
	// 이 문제를 해결하기 위해 스프링의 관점 지향 프로그래밍(Aspect Oriented Programming, AOP)를 적용한다.
	//
	// 모든 Controller 클래스의 메소드에 대해서 ValidationCheckAdvice객체의 validationCheck() 메소드가 동작하여
	// 사용자가 입력한 값의 유효성을 검사한다.
	//
	// 혼동하기 쉬운 개념
	// 필터 : 서블릿이 수행되기 전에 동작하여 서블릿에 대한 사전/사후 처리를 담당한다.
	// 인터셉터 : 컨트롤러가 수행되기 전에 동작하여 컨트롤러에 대한 사전/사후 처리를 담당한다.
	// AOP는 컨트롤러나 서비스 클래스 모두에서 동작하도록 적용할 수 있다.
	@Around("execution(* com.arsyux..controller.*Controller.*(..))")
	public Object validationCheck(ProceedingJoinPoint jp) throws Throwable {
		
		Object[] args = jp.getArgs();
		
		for(Object arg : args) {
			// 인자 목록에 BindingResult의 객체가 있다면
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				if (bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					// 에러 메시지를 Map에 저장한다.
					for	(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
				}
			}
		}
		
		
		return jp.proceed();
	}
	
}
