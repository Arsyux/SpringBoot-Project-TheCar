package com.arsyux.thecar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.arsyux.thecar.service.UserDetailsServiceImpl;


// 스프링 시큐리티
// 스프링 시큐리티는 애플리케이션의 인증(authentication)과 인가(authorization)를 일관된 형태로 처리하는 모듈이다.
// 애플리케이션에서는 인증을 통해 사용자를 식별하고 인가로 시스템 자원에 대한 접근을 통제한다.
//
// 소스 코드 정리
// 스프링 시큐리티를 적용함에따라 기존에 작성했던 코드인 AuthenticateInterceptor.java, LoginController.java, login.js는 주석처리하였다.
// TheCarWebMvcConfiguration에서도 인증 여부를 확인하는 AuthenticateInterceptor 클래스를 주석처리하였다.
// pom.xml에 시큐리티 관련 라이브러리를 추가하고 재실행하면 시큐리티 관련 자동설정 클래스가 동작하여 다양한 객체가 생성된다.
//
// 기본 인증 확인
// 스프링 시큐리티의 기본 설정값으로 모든 페이지에 대한 접근이 인증에 성공한 사용자에 한하여 제공된다.
// 인덱스 페이지를 보기 위해서는 반드시 로그인 인증에 성공해야하며, 이때, 인증에 사용할 아이디와 비밀번호는 스프링에서 제공한다.
// 아이디는 user이고 비밀번호는 애플리케이션 실행 순간 콘솔에 출력된다.
// 인증에 성공할경우 정상적으로 인덱스 페이지를 볼 수 있지만, 다른 작업을 위해선(글쓰기등) 인증 상태를 세션기반으로 유지해야한다.
//
// 인증 상태 유지
// 스프링 시큐리티는 인증에 성공한 사용자의 정보를 자동으로 세션에 등록한다.
// 이렇게 세션에 등록된 사용자 정보를 JSP 파일에서 사용하기 위해서는 시큐리티가 제공하는 커스텀 태그를 사용해야한다.
// -> pom.xml에 라이브러리 추가
//
// 시큐리티 커스터마이징
// 스프링이 제공하는 시큐리티는 환경 설정 클래스나 외부 프로퍼티 (application.yml)를 이용하여 커스터마이징 할 수 있다.
// 기존의 환경 설정 클래스인 TheCarWebConfiguration 클래스를 재사용할 수 있지만, 코드가 너무 복잡해지는 것을 막기위해
// 스프링 시큐리티만을 위한 새로운 환경 설정 클래스인 TheCarWebSecurityConfiguration를 작성한다.
//
// @EnableWebSecurity
// @EnableWebSecurity 어노테이션을 추가할 경우 메시지가 출력되는데
// 클래스 패스에 @EnableWebSecurity어노테이션이 사용된 설정 클래스가 존재하면 WebSecurityEnablerConfiguration이라는 자동 설정 클래스는
// 더 이상 동작하지 않음을 의미한다. 이는 사용자가 원하는 방향으로 시큐리티를 커스터마이징 할 수 있다는 것을 뜻하기도 한다.
//
// extends WebSecurityConfigurerAdapter
// 환경 설정 클래스가 @EnableWebSecurity를 가지고 WebSecurityConfigurerAdapter 클래스를 상속하면, 자동 설정 클래스에 의해 제공되던 시큐리티 관련
// 객체들이 더 이상 제공되지 않는다.
@Configuration
@EnableWebSecurity
public class TheCarWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	// 환경 설정 클래스를 수정하여 스프링 시큐리티가 기본적으로 사용하던 UserDetailsService 객체가 아닌
	// UserDetailsServiceImpl 객체를 이용하도록 한다.
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	// 비밀번호 암호화
	// BCrytPasswordEncoder 객체를 생성하는 passwordEncoder() 메소드를 추가한다.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	// 사용자가 입력한 username으로 User객체를 검색하고 password를 비교한다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// AuthenticationManagerBuilder가 AuthenticationManager를 생성할 때 UserDetailsService를 이용하도록 설정했다.
		// 이제 더 이상 스프링에서 기본 계정을 제공하지 않으며 이제부터는 로그인이 필요한 경우 USERS 테이블에 등록된 회원 정보를 이용해야 한다.
		//
		// configure() 메소드에서 UserDetilsService 객체로 인증을 처리할 때 BCrytPassworEncoder를 이용하도록 추가한다.
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	// 시큐리티 권한 제어
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 인증 없이 접근을 허용하는 경로
		// antMatcher() 메소드를 이용하여 특정 경로에 대한 접근은 인증 없이 모두 허용한다.
		http.authorizeRequests().antMatchers("/webjars/**", "/js/**", "/image/**", "/", "/auth/**", "/css/**").permitAll();
		
		// 나머지 경로는 인증이 필요하도록 설정한다.
		// 이제 로그인 하지 않은 상태에서 인덱스 화면까지는 볼 수 있으나 <상세보기> 버튼을 클릭하면 403접근 에러가 발생한다.
		http.authorizeRequests().anyRequest().authenticated();
		
		// HTTP 요청에서 사이트 간 요청 위조(Cross Site Request Forgery, CSRF) 토큰을 받지 않기 위한 설정을 추가한다.
		// 이를 통해 CSRF 토큰이 없는 HTTP 요청에 대해서도 로그인 인증이 동작할 것이다.
		// CSRF 공격이란?
		// 관리자의 의도와 상관없이 시스템의 데이터를 조작하도록 만드는 것을 CSRF 공격이라고 한다.
		// 이러한 문제를 방지하는 방법으로 CSRF 토큰을 사용한다.
		// 사용자로부터 요청이 들어오면 CSRF 토큰을 생성하여 세션에 저장하는 것이다.
		// 그리고 이후 모든 요청에 대해서 CSRF 토큰을 전송하도록 하면 CSRF 토큰 없이 외부로부터 들어오는 모든 요청을 차단할 수 있다.
		http.csrf().disable();
		
		// 로그인 화면 설정
		// 기본 로그인 화면 제공
		// 사용자가 인덱스 화면에서 포스트 상세 화면으로 이동하기 위해서는 반드시 인증에 성공해야 한다.
		// 이때, 스프링 시큐리티에서 제공하는 로그인 화면을 이용하기 위해 환경 설정 클래스를 다음과 같이 수정한다.
		//http.formLogin();
		// 사용자 정의 로그인
		http.formLogin().loginPage("/auth/login");
		
		// 로그인 요청 URI를 변경한다.
		http.formLogin().loginProcessingUrl("/auth/securitylogin");
		
		// 로그 아웃 처리
		// 사용자가 /auth/logout 요청을 서버에 전송하면 로그아웃되며 인덱스 페이지("/")로 이동하도록 설정한다.
		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
	}
	
}
