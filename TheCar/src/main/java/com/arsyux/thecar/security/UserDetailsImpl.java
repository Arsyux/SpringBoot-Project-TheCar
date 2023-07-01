package com.arsyux.thecar.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.arsyux.thecar.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails, OAuth2User {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private Map<String, Object> attributes;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	
	public UserDetailsImpl(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {  
		return user.getRealname();
	}
	
	@Override
	public String getPassword() {
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
		roleList.add(new GrantedAuthority() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return "ROLE_" + user.getRole();
			}
		});
		return roleList;
	}
	
}
