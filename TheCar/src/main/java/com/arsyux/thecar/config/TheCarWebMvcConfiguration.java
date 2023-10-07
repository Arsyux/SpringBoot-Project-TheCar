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
		// ResourceBundleMessageSource 객체는 messageSource로 시작하는 2개의 메시지 파일(messageSource_en.properties,
		// messageSource_ko.properties)에 등록된 메시지를 메모리에 로딩한다.
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message/messageSource");
		return messageSource;
	}
	
	// SessionLocaleResolver 객체는 브라우저로부터 전송된 로케일을 추출하여 세션에 등록하고, 세션이 종료될 때까지
	// 해당 로케일을 유지시킨다.
	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	// 브라우저의 로케일에 따라 자동으로 알맞은 언어의 메시지가 적용되도록 기능을 구현하였으나
	// 브라우저의 로케일과 상관 없이 다른 언어로 메시지를 변경하고 싶은 경우 LocaleChangeInterceptor를 사용한다.
	// LocaleChangeInterceptor 객체에서는 setParamName() 메소드가 로케일 정보를 전달받을 인자를 지정한다.
	// 이제 lang이라는 인자로 로케일 정보가 전달되면 LocaleChangeInterceptor객체가 기존의 로케일을 전송받은 로케일로 변경해준다.
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	

}
