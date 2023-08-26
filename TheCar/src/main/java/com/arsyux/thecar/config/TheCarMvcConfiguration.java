package com.arsyux.thecar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TheCarMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터 적용
		// 사이트에 접속하는 순간 AuthenticateInterceptor 클래스의 preHandle() 메소드가 동작한다. 
		// preHandle메소드에 따라 세션에 정보가 없으면 무조건 로그인 화면으로 이동할 것이다.
		// addPathPatterns를 통해 인증이 필요한 경로를 계속 추가할 수 있다.
		registry.addInterceptor(new AuthenticateInterceptor()).addPathPatterns("/");
	}
	
}
