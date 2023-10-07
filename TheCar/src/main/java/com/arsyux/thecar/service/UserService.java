package com.arsyux.thecar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.User;
//import com.arsyux.thecar.persistence.UserRepository;
import com.arsyux.thecar.persistence.UserDAO;

// 문법적으로는 컨트롤러가 리포지터리를 바로 호출해도 되지만, 일반적으로 컨트롤러는 서비스 클래스를 호출하고
// 서비스 클래스에서 리포지터리를 사용한다.
// 이렇게 컨트롤러와 리포지터리 사이에 서비스 클래스를 추가하면 서비스 클래스에서 여러 리포지터리를 이용함으로써
// 하나의 트랜잭션으로 여러 데이터베이스 관련 작업을 처리할 수 있다.
@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 회원가입
	@Transactional
	public void insertUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userDAO.insertUser(user);
	}
	
	// 회원 정보 조회
	/*
	@Transactional(readOnly = true)
	public User getUser(User user) {
		User findUser = userDAO.getUser(user);
		if(findUser == null) { findUser = new User(); }
		return findUser;
	}
	*/
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		User findUser = userDAO.findByUsername(username);
		if(findUser == null) { findUser = new User(); }
		return findUser;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// 스프링 시큐리티의 인증 처리 과정 - 세션 갱신
	// 회원 정보를 수정한 후에 수정된 회원 정보로 세션을 갱신하기 위해서는 스프링 시큐리리티의 인증 처리 과정을 이해해야한다.
	// 스프링 시큐리티는 사용자가 입력한 정보를 바탕으로 회원을 조회하고, 조회된 정보를 이용하여 Authentication을 생성한다.
	// 그리고 이렇게 생성된 Authentication이 자동으로 SecurityContext객체에 등록되는데, 이때 Authentication을 포함하는
	// SecurityContext는 자동으로 HttpSession에 등록된다는 것이 중요하다.
	// 이렇게 세션에 등록된 SecurityContext에는 컨트롤러에서 @AuthenticationPrincipal 어노테이션을 이용하여 접근할 수 있다.
	@Transactional
	public User updateUser(User user) {
		//User findUser = userRepository.findById(user.getId()).get();
		//findUser.setUsername(user.getUsername());
		//findUser.setPassword(passwordEncoder.encode(user.getPassword()));
		//findUser.setEmail(user.getEmail());
		
		//return findUser;
		return new User(1, "test", "123");
	}
}
