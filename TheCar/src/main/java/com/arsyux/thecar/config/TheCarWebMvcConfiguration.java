package com.arsyux.thecar.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// 환경설정 클래스
@Configuration
public class TheCarWebMvcConfiguration implements WebMvcConfigurer {
	
	// 스프링 컨테이너가 ModelMapper를 생성할 수 있도록 @Bean 어노테이션 등록
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	// 다국어 설정 적용
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
		
	// 다국어 처리
	@Bean("messageSource")
	public MessageSource messageSource() {
		// messageSource_en.properties, messageSource_ko.properties에 등록된 메시지를 메모리 로딩
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message/messageSource");
		return messageSource;
	}
	
	// SessionLocaleResolver : 로케일 추출 및 등록, 세션이 종료될 때까지 로케일 유지
	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	// LocaleChangeInterceptor : 파라미터 lang으로 로케일 정보를 전달하면 기존의 로케일을 변경해줌
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	

}
