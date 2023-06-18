package com.arsyux.thecar.config;                                               

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.arsyux.thecar.security.OAuth2UserDetailsServiceImpl;
import com.arsyux.thecar.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class TheCarWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private OAuth2UserDetailsServiceImpl oauth2DetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public AuthenticationManager authticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/webjars/**", "/js/**", "/image/**", "/", "/auth/**", "/oauth/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.csrf().disable();
		http.formLogin().loginPage("/auth/login");
		http.formLogin().loginProcessingUrl("/auth/securitylogin");
		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
		
		http.oauth2Login()
		.userInfoEndpoint()
		.userService(oauth2DetailsService);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
