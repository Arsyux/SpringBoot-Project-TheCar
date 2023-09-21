package com.arsyux.thecar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.persistence.UserRepository;
import com.arsyux.thecar.security.UserDetailsImpl;

// 사용자가 입력한 아이디 정보로 실질적인 DB연동이 되도록 UserDetailsService 인터페이스를 구현한다.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	// loadUserByUsername() 메소드는 매개변수로 받은 사용자 아이디를 이용하여 com.arsyux.domain.User엔티티를 검색한다.
	// 그리고 검색 결과를 바탕으로 앞서 작성한 UserDetailsImpl 객체를 생성하여 반환한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username).orElseThrow(()->{
			return new UsernameNotFoundException(username + " 사용자가 없습니다.");
		});
		return new UserDetailsImpl(principal);
	}
	
	
}
