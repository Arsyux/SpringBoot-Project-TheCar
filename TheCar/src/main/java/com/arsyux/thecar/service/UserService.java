package com.arsyux.thecar.service;

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

	@Transactional(readOnly = true)
	public User getUser(String username) {
		User findUser = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});

		return findUser;
	}

	@Transactional
	public User updateUser(User user) {
		User findUser = userRepository.findById(user.getId()).get();
		findUser.setId(user.getId());
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));
		//findUser.setEmail(user.getEmail());
		
		return findUser;
	}

}
