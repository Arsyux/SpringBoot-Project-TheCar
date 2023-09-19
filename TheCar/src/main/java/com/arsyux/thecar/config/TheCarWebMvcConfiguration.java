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

@Configuration
public class TheCarWebMvcConfiguration implements WebMvcConfigurer {
	
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
		// 다국어 설정 적용
		registry.addInterceptor(localeChangeInterceptor());
	}
		
	// 다국어 설정
	// 사용자가 원하는 언어로 메시지를 출력하려면, 가장 먼저 각 언어에 해당하는 메시지 파일을 작성해야 한다.
	// 기본적으로 메시지 파일의 확장자는 properties이고 파일명은 언어에 해당하는 로케일(locale) 정보를 결합하여 지정한다.
	// => resource 폴더 하위에 message 폴더를 생성한 후 영어와 한글 언어를 서비스하기 위한 messageSource_en.properties파일과
	// messageSource_ko.properties파일을 생성한다.
	//
	// 다국어 처리
	// 웹 애플리케이션에 다국어를 적용하기 위해서는 스프링에서 제공하는 2가지 객체가 필요하다.
	// 첫 번째는 방금 작성한 메시지 파일을 로딩하는 MessageSource객체고,
	// 두번째는 브라우저에서 전송한 로케일 정보를 추출하여 유지하는 LocaleResolver 객체다.
	// 따라서 프로젝트의 설정 클래스인 TheCarWebMvcConfiguration 클래스에 이 2가지 객체를 등록한다.
	@Bean("messageSource")
	public MessageSource messageSource() {
		// ResourceBundleMessageSource 객체는 messageSource로 시작하는 2개의 메시지 파일(messageSource_en.properties,
		// messageSource_ko.properties)에 등록된 메시지를 메모리에 로딩한다.
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message/messageSource");
		return messageSource;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		// SessionLocaleResolver 객체는 브라우저로부터 전송된 로케일을 추출하여 세션에 등록하고, 세션이 종료될 때까지
		// 해당 로케일을 유지시킨다.
		// 참고) 스프링은 SessionLocaleResolver 외에도 AcceptHeaderLocaleResolver, CookieLocaleResolver,
		// FixedLocaleResolver도 제공한다.
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
