package com.arsyux.thecar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.jblog.domain.OAuthType;
import com.arsyux.jblog.domain.RoleType;
import com.arsyux.jblog.domain.User;
import com.arsyux.thecar.persistence.UserRepository;

@Service
public class UserService {

	// 일반적으로 컨트롤러는 서비스 클래스를 호출하고 서비스 클래스에서 리포지터리를 사용한다.
	// 이렇게 컨트롤러와 리포지터리 사이에 서비스 클래스를 추가하는 이유는
	// 하나의 서비스 클래스에서 여러 리포지터리를 이용함으로써
	// 하나의 트랜잭션으로 여러 데이터베이스 관련 작업을 처리하기 위해서다.
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void insertUser(User user) {
		// 비밀번호를 암호화하여 설정
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(RoleType.USER);
		if(user.getOauth() == null) {
			user.setOauth(OAuthType.JBLOG);
		}
		userRepository.save(user);
	}

	// 원래 INSERT, UPDATE, DELETE 기능은 트랙잭션과 관련된 데이터 조작 언어(DML) 작업이기 떄문에
	// 비지니스 메소드에 @Transactional을 설정해야함.
	// 반면 검색 기능의 메소드는 트랜잭션과 무관하기 때문에 @Transactional 설정이 필요없다.
	// 하지만 getUser()메소드에는 SELECT 기능을 구현하였으므로 @Transactional(readOnly = true)를
	// 설정하여 메소드가 종료될 때까지 데이터 정합성을 유지하도록 한다.
	@Transactional(readOnly = true)
	public User getUser(String username) {
		User findUser = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});

		return findUser;
	}

	@Transactional
	public User updateUser(User user) {
		// 스프링 시큐리티는 사용자가 입력한 정보를 바탕으로 회원을 조회하고,
		// 조회된 정보를 이용하여 Authenrication을 생성함
		// 이렇게 생성된 Authentication이 자동으로 SecurityContext 객체에 등록됨
		// 이때 Authentication을 포함하는 SecurityContext는 자동으로 HttpSession에 등록된다는 것이 중요함.
		// 이렇게 세션에 등록된 SecurityContext에는 컨트롤러에서 @AuthenticationPrincipal 어노테이션을 이용하여 접근
		User findUser = userRepository.findById(user.getId()).get();
		findUser.setUsername(user.getUsername());
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));
		findUser.setEmail(user.getEmail());
		
		return findUser;
	}

}
