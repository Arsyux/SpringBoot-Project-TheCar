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

import com.arsyux.thecar.security.UserDetailsServiceImpl;

// 시큐리티 설정 클래스
@Configuration
@EnableWebSecurity
public class TheCarWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	// 기본적으로 사용하는 UserDetailsService에서 UserDetailsServiceImpl 객체를 이용하도록 수정
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// 스프링 컨테이너가 PasswordEncoder를 생성할 수 있도록 @Bean 어노테이션 등록
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
		// 인증 없이 접근을 허용하는 경로 설정
		http.authorizeRequests().antMatchers("/webjars/**", "/js/**", "/image/**", "/", "/auth/**", "/css/**").permitAll();
		
		// 나머지 경로는 인증이 필요하도록 설정
		http.authorizeRequests().anyRequest().authenticated();
		
		// CSRF 토큰 거부 설정
		http.csrf().disable();
		
		// 로그인 화면 설정
		http.formLogin().loginPage("/auth/loginUser");
		
		// 로그인 요청 URI 변경
		http.formLogin().loginProcessingUrl("/auth/securitylogin");
		
		// 로그 아웃 처리
		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
	}
	
}
