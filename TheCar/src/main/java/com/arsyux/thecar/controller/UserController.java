package com.arsyux.thecar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.RoleType;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.persistence.UserRepository;

// 비즈니스 컴포넌트가 사용자 요청을 처리하는 과정에서 데이터베이스 연동이 필요할 때, 리포지터리가 서비스 객체에 의해서 사용된다.
@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	// REST 컨트롤러를 구현할 때는 등록 기능의 메소드에 @PostMapping 어노테이션을 설정한다.
	@PostMapping("/user")
	public @ResponseBody String insertUser(@RequestBody User user) {
		// insertUser() 메소드는 JSON으로 전달된 회원 정보를 User 객체로 받기 위해 User 타입의 매개변수를 가진다.
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return user.getUsername() + "회원가입 성공";
	}
	
	// REST 컨트롤러를 구현할 때, 조회 기능의 메소드에는 @GetMapping을 설정한다.
	@GetMapping("/user/get/{id}")
	public @ResponseBody User getUser(@PathVariable int id) {
		// getUser()메소드는 요청 URL에 포함된 id 정보를 매개변수인 id에 할당하기 위해 @PathVariable을 사용한다.
		// 그 후, findById()메소드를 통해 특정 아이디에 해당하는 User 엔티티를 검색하고 Optional 타입의 객체를 반환한다.
		// 이를 get() 메소드를 사용하여 타입을 User로 변환한다.
		User findUser = userRepository.findById(id).get();
		
		// Optional의 get() 메소드는 검색된 엔티티가 없는 경우, NoSuchElementException을 발생시킨다.
		// -> Exception 처리
		return findUser;
	}
	
}
