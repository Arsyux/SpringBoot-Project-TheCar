package com.arsyux.thecar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.RoleType;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.persistence.UserRepository;

// 문법적으로는 컨트롤러가 리포지터리를 바로 호출해도 되지만, 일반적으로 컨트롤러는 서비스 클래스를 호출하고
// 서비스 클래스에서 리포지터리를 사용한다.
// 이렇게 컨트롤러와 리포지터리 사이에 서비스 클래스를 추가하면 서비스 클래스에서 여러 리포지터리를 이용함으로써
// 하나의 트랜잭션으로 여러 데이터베이스 관련 작업을 처리할 수 있다.
@Service
public class UserService {

	// JPA기반의 데이터베이스 연동을 UserService 클래스에서 처리하기 위해 UserRepository 객체 의존성을 주입한다.
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// @Transactional은 비즈니스 메소드에서 예외가 발생할 때 해당 메소드에 대한 트랜잭션을 ROLLBACK하고
	// 정상 종료될 때는 트랜잭션을 자동으로 COMMIT한다.
	@Transactional
	public void insertUser(User user) {
		// 비밀번호를 암호화하여 설정한다.
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setRole(RoleType.USER);
		user.setMileage(0);
		
		userRepository.save(user);
	}
	
	// INSERT, UPDATE, DELETE 기능은 트랙잭션과 관련된 데이터 조작 언어(Data Manipulation Language, DML) 작업이기 때문에
	// 비즈니스 메소드에 @Transactional을 설정해야한다. 반면, 검색 기능의 메소드는 트랜잭션과 무관하기 때문에 @Transactional 설정이 필요없다.
	// 하지만 getUser()메소드에는 SELECT 기능을 구현하였으므로 @Transactional(readOnly = true)를 설정하여 메소드가 종료될 때까지
	// 데이터 정합성을 유지하도록 한다.
	@Transactional(readOnly = true)
	public User getUser(String username) {
		// getUser()메소드는 UserRepository 객체를 이용하여 회원 정보(User 엔티티)를 검색 후 반환한다.
		// 회원 정보 검색 결과가 없을 때는 널(null)이 아닌 아무런 값도 설정되지 않은 빈 User 객체를 반환하도록 한다.
		User findUser = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		
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
		User findUser = userRepository.findById(user.getId()).get();
		findUser.setUsername(user.getUsername());
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));
		findUser.setEmail(user.getEmail());
		
		return findUser;
	}
}
