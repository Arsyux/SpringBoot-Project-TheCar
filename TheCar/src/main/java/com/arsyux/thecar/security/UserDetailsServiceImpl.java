package com.arsyux.thecar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.persistence.UserDAO;

// 사용자가 입력한 정보를 바탕으로 UserDetails 타입의 객체를 생성하는 클래스
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userDAO.findByUsername(username);
		
		// 검색한 id가 존재하지 않을 경우 UsernameNotFoundException을 throw한다.
		if(principal.getUsername() == null) { throw new UsernameNotFoundException(username + " 사용자가 없습니다."); }
		
		return new UserDetailsImpl(principal);
	}
	
}
