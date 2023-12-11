package com.arsyux.thecar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.UserVO;
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
	
	// ========================================
	// 1. 회원가입
	// ========================================
	
	// 회원가입
	@Transactional
	public void insertUser(UserVO user) {
		// 비밀번호 암호화
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// 회원 정보 저장
		userDAO.insertUser(user);
	}
	
	// ========================================
	// 2. 회원검색
	// ========================================
	
	// 로그인 아이디로 회원 정보 조회
	@Transactional(readOnly = true)
	public UserVO findByUsername(String username) {
		// 로그인 아이디로 회원정보 검색
		UserVO findUser = userDAO.findByUsername(username);
		// 검색된 유저가 없을경우 처리
		if(findUser == null) { findUser = new UserVO(); }
		return findUser;
	}
	// 휴대폰으로 회원 정보 조회
	@Transactional(readOnly = true)
	public UserVO findByPhone(String phone) {
		// 휴대폰으로 회원정보 검색
		UserVO findUser = userDAO.findByPhone(phone);
		// 검색된 유저가 없을경우 처리
		if(findUser == null) { findUser = new UserVO(); }
		return findUser;
	}
	// 휴대폰으로 회원 정보 조회
	@Transactional(readOnly = true)
	public UserVO findByEmail(String email) {
		// 휴대폰으로 회원정보 검색
		UserVO findUser = userDAO.findByEmail(email);
		// 검색된 유저가 없을경우 처리
		if(findUser == null) { findUser = new UserVO(); }
		return findUser;
	}
	
	// 아이디 찾기
	@Transactional(readOnly = true)
	public UserVO findUsername(UserVO user) {
		// 유저정보로 검색
		UserVO findUser = userDAO.findUsername(user);
		// 검색된 유저가 없을경우 처리
		if(findUser == null) { findUser = new UserVO(); }
		return findUser;
	}
	// 비밀번호 찾기
	@Transactional(readOnly = true)
	public UserVO findPassword(UserVO user) {
		// 유저정보로 검색
		UserVO findUser = userDAO.findPassword(user);
		// 검색된 유저가 없을경우 처리
		if(findUser == null) { findUser = new UserVO(); }
		return findUser;
	}

	// ========================================
	// 2. 회원 정보 수정
	// ========================================
	
	// 회원 정보 수정
	@Transactional
	public UserVO updateUser(UserVO user) {
		
		UserVO findUser = userDAO.findById(user.getUserid());
		
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));

		userDAO.updateUser(findUser);
		return findUser;
	}
	
	// 비밀번호 변경
	@Transactional
	public UserVO changePassword(UserVO user) {
		UserVO findUser = userDAO.findById(user.getUserid());
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.changePassword(findUser);
		return findUser;
	}

	// ========================================
	// 회원 탈퇴
	// ========================================
	
	// 회원 탈퇴
	@Transactional
	public void deleteUser(UserVO user) {
		//UserVO findUser = userDAO.findById(user.getUserid());
		//findUser.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.deleteUser(user);
	}
	
	
}
