package com.arsyux.thecar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arsyux.thecar.domain.UserVO;
import com.arsyux.thecar.persistence.UserDAO;

// 사용자가 입력한 정보를 바탕으로 UserDetails 타입의 객체를 생성하는 클래스
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO principal = userDAO.findByUsername(username);
		
		// 검색한 id가 존재하지 않을 경우 UsernameNotFoundException을 throw한다.
		if(principal == null) { throw new UsernameNotFoundException("존재하지 않는 아이디입니다."); }

		return new UserDetailsImpl(principal);
	}
	
}
