package com.arsyux.thecar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.UserVO;
import com.arsyux.thecar.persistence.UserDAO;

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
	
	// 게시글을 작성한 유저 찾기
	@Transactional(readOnly = true)
	public UserVO findByPostId(int postid) {
		// 유저정보로 검색
		UserVO findUser = userDAO.findByPostId(postid);
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
		userDAO.deleteUser(user);
	}
	
	// ========================================
	// 포인트 적립
	// ========================================
	@Transactional
	public void updatePoint(UserVO user) {
		userDAO.updatePoint(user);
	}
	
	
}
