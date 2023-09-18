package com.arsyux.thecar.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TheCarMvcConfiguration implements WebMvcConfigurer {
	
	// 스프링 컨테이너가 ModelMapper를 생성할 수 있도록 @Bean 어노테이션으로 메소드를 등록한다.
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터 적용
		// 사이트에 접속하는 순간 AuthenticateInterceptor 클래스의 preHandle() 메소드가 동작한다. 
		// preHandle메소드에 따라 세션에 정보가 없으면 무조건 로그인 화면으로 이동할 것이다.
		// addPathPatterns를 통해 인증이 필요한 경로를 계속 추가할 수 있다.
		
		// 로그인 인증에 성공한 사용자에게만 포스트 상세 화면을 제공하기 위해 post가 포함된 모든 요청에 대해서도 동작하도록 수정한다.
		registry.addInterceptor(new AuthenticateInterceptor()).addPathPatterns("/").addPathPatterns("/post/**");
	}
	
}
