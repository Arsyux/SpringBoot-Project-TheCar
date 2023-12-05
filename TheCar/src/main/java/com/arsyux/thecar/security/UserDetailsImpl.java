package com.arsyux.thecar.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arsyux.thecar.domain.UserVO;

import lombok.Getter;
import lombok.Setter;

// 검색한 사용자 정보를 저장할 클래스
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	// USER 테이블과 매핑된 엔티티
	private UserVO user;
	
	public UserDetailsImpl(UserVO user) {
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
		// 권한 목록 설정 - 람다식 사용
		roleList.add(() -> {
			return "ROLE_";// + user.getRole();
		});
		
		return roleList;
	}
	
}