package com.arsyux.thecar.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arsyux.thecar.domain.RoleType;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.persistence.UserRepository;

// 문법적으로는 컨트롤러가 리포지터리를 바로 호출해도 되지만, 일반적으로 컨트롤러는 서비스 클래스를 호출하고
// 서비스 클래스에서 리포지터리를 사용한다.
// 이렇게 컨트롤러와 리포지터리 사이에 서비스 클래스를 추가하면 서비스 클래스에서 여러 리포지터리를 이용함으로써
// 하나의 트랜잭션으로 여러 데이터베이스 관련 작업을 처리할 수 있다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void insertUser(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
}
