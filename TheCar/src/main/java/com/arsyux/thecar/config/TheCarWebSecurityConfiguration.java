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
		http.authorizeRequests().antMatchers("/webjars/**", "/js/**", "/image/**", "/", "/auth/**", "/css/**").permitAll();
		
		// 나머지 경로는 인증이 필요하도록 설정
		http.authorizeRequests().anyRequest().authenticated();
		
		// HTTP 요청에서 사이트 간 요청 위조(Cross Site Request Forgery, CSRF) 토큰을 받지 않기 위한 설정을 추가한다.
		http.csrf().disable();
		
		// 로그인 화면 설정
		// 기본 로그인 화면 제공
		// 사용자가 인덱스 화면에서 포스트 상세 화면으로 이동하기 위해서는 반드시 인증에 성공해야 한다.
		// 사용자 정의 로그인
		http.formLogin().loginPage("/auth/loginUser");
		
		// 로그인 요청 URI를 변경한다.
		http.formLogin().loginProcessingUrl("/auth/securitylogin");
		
		// 로그 아웃 처리
		// 사용자가 /auth/logout 요청을 서버에 전송하면 로그아웃되며 인덱스 페이지("/")로 이동하도록 설정한다.
		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
	}
	
}
