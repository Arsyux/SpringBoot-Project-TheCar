package com.arsyux.thecar.service;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.OAuthType;
import com.arsyux.thecar.domain.RoleType;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.persistence.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public User getUserByPhone(String phone) {
		User findUser = userRepository.findByPhone(phone).orElseGet(() -> {
			return new User();
		});

		return findUser;
	}
	
	@Transactional(readOnly = true)
	public User getUserByUsername(String username) {
		User findUser = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});

		return findUser;
	}
	
	@Transactional
	public void insertUser(User user) {
		
		// 비밀번호를 암호화하여 설정
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// 포인트
		user.setPoint(0);
		
		user.setRole(RoleType.USER);
		if(user.getOauth() == null) {
			user.setOauth(OAuthType.THECAR);
		}
		
		userRepository.save(user);
	}
	
	@Transactional
	public User updateUser(User user) {
		User findUser = userRepository.findByUsername(user.getUsername()).get();
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));
		findUser.setPoint(user.getPoint());;
		return findUser;
	}

}
