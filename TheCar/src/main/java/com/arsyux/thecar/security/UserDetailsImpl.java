package com.arsyux.thecar.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arsyux.thecar.domain.User;

import lombok.Getter;
import lombok.Setter;

// JPA 연동
// JPA를 연동하여 USERS 테이블에 등록된 회원 정보로 로그인 인증이 처리되도록 한다.
//
// 스프링 시큐리티 아키텍처
// 스프링 시큐리티와 JPA를 연동하려면 시큐리티 아키텍처를 이해해야 한다.
// 1. 사용자가 아이디와 비밀번호를 입력하고 로그인 요청(/auth/securitylogin)을 전송한다.
// 2. AuthenticationFilter가 사용자의 요청을 가로채서 username과 password를 기반으로 UsernamePasswordAuthenticationToken 객체를 생성한다.
// 3. AuthenticationFilter는 AuthenticationManager를 구현한 ProviderManager의 authenticate() 메소드를 호출할 때,
//    UsernamePasswordAuthenticationToken을 인자로 전달하면서 인증을 요청한다.
// 4. ProviderManager는 AuthenticationProvider 객체 중 하나를 이용하여 실질적인 인증을 처리하고 그 결과로 Authentication 객체를 반환한다.
// 5. AuthenticationProvider는 UserDetailsService 객체를 이용하여 데이터베이스에 있는 회원 정보를 검색한다.
// 6. UserDetailsService는 검색 결과를 바탕으로 UserDetails를 구현한 User 객체를 생성한다.
//
// 스프링이 제공하는 시큐리티를 커스터마이징하여 JPA와 연동하기 위해 2개의 클래스를 추가로 작성해야한다.
// 첫 번째는 검색한 사용자 정보를 저장할 UserDetails 클래스이고,
// 두 번째는 사용자가 입력한 정보를 바탕으로 UserDetails 타입의 객체를 생성하는 UserDetailsService 클래스다.
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
	
	// UserDetailsImpl 클래스에서 UserDetails 인터페이스를 구현했으므로 UserDetails의 추상 메소드를 모두 오버라이딩한다.
	// 이때 생성자 매개변수로 받은 User 객체의 설정값을 적절히 이용하여 오버라이딩된 메소드들을 구현한다.
	
	private static final long serialVersionUID = 1L;
	
	// USERS 테이블과 매핑된 엔티티
	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		// noop: 암호화하지 않기 위한 설정
		//return "{noop}" + user.getPassword();
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 반환
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// 계정이 잠겨있는지 반환
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	// 비밀번호가 만료되지 않았는지 반환
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화되었는지 반환
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 가지고 있는 권한 목록 저장하여 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한 목록
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		// 권한 목록 설정
		/*
		roleList.add(new GrantedAuthority() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return "ROLE_" + user.getRole();
			}
			
		});
		*/
		// 권한 목록 설정 - 람다식 사용
		//roleList.add(() -> {
		//	return "ROLE_" + user.getRole();
		//});
		
		return roleList;
	}
	
}
