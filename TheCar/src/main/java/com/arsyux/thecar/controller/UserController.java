package com.arsyux.thecar.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.RoleType;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.exception.TheCarException;
import com.arsyux.thecar.persistence.UserRepository;

// 비즈니스 컴포넌트가 사용자 요청을 처리하는 과정에서 데이터베이스 연동이 필요할 때, 리포지터리가 서비스 객체에 의해서 사용된다.
@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	// 삽입기능
	// REST 컨트롤러를 구현할 때는 등록 기능의 메소드에 @PostMapping 어노테이션을 설정한다.
	// insertUser() 메소드는 JSON으로 전달된 회원 정보를 User 객체로 받기 위해 User 타입의 매개변수를 가진다.
	@PostMapping("/user")
	public @ResponseBody String insertUser(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return user.getUsername() + "회원가입 성공";
	}

	// 조회기능
	// REST 컨트롤러를 구현할 때, 조회 기능의 메소드에는 @GetMapping을 설정한다.
	@GetMapping("/user/get/{id}")
	public @ResponseBody User getUser(@PathVariable int id) {
		// getUser()메소드는 요청 URL에 포함된 id 정보를 매개변수인 id에 할당하기 위해 @PathVariable을 사용한다.
		// 그 후, findById()메소드를 통해 특정 아이디에 해당하는 User 엔티티를 검색하고 Optional 타입의 객체를 반환한다.
		// 이를 get() 메소드를 사용하여 타입을 User로 변환한다.
		// User findUser = userRepository.findById(id).get();

		// Optional의 get() 메소드는 검색된 엔티티가 없는 경우, NoSuchElementException을 발생시킨다.
		// -> Exception 처리가 필요하며, 이를 TheCarException으로 처리한다.
		// User findUser = userRepository.findById(id).orElseThrow(new
		// Supplier<TheCarException>() {
		// @Override
		// public TheCarException get() {
		// return new TheCarException(id +"번 회원이 없습니다.");
		// }
		// });

		// 람다식으로 처리
		User findUser = userRepository.findById(id).orElseThrow(() -> {
			return new TheCarException(id + "번 회원이 없습니다.");
		});
		return findUser;
	}

	// 수정기능
	// REST 컨트롤러를 구현할 때 수정 기능의 메소드에는 @PutMapping 어노테이션을 사용한다.
	// @JPA는 수정 작업을 처리할 때 테이블의 모든 컬럼을 수정하는 UPDATE 구문을 사용한다.
	// 이렇게 모든 컬럼 수정 원칙을 적용하면 하나의 UPDATE로 다양한 수정을 처리할 수 있기 때문에 효율적이다.
	// 수정 기능을 구현할 때 save() 메소드를 호출하지않고 @Transactional 어노테이션을 적용해도 결과는 같다.
	@Transactional
	@PutMapping("/user")
	public @ResponseBody String updateUser(@RequestBody User user) {
		User findUser = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new TheCarException(user.getId() + "번 회원이 없습니다.");
		});
		findUser.setUsername(user.getUsername());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());

		// @Transactional 어노테이션을 사용하면 메소드가 호출될 때 트랜잭션이 시작되고, 메소드가 종료되면 트랜잭션이 자동으로 종료된다.
		// 이때, COMMIT 요청이 JPA 컨테이너에 전달되고 JPA 컨테이너는 값이 변경된 엔티티에 대한 UPDATE를 작성하여 데이터베이스에 전송한다.
		// 원래는 컨트롤러 메소드가 아니라 서비스 메소드에 @Transactional을 적용해야한다. (빠른 테스트를 위함)
		// userRepository.save(findUser);
		return "회원 정보 수정 성공";
	}

	// 삭제기능
	// REST 컨트롤러를 구현할 때, 삭제 기능의 메소드에는 @DeleteMapping을 설정한다.
	// 요청 URL에 포함된 id 정보를 추출하기 위해 @PathVariable 어노테이션을 사용한다.
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		return id + "번 회원이 삭제되었습니다.";
	}

	// 회원 목록
	@GetMapping("/user/list")
	public @ResponseBody List<User> getUserList() {
		return userRepository.findAll();
	}

	// 페이징 처리1
	// 스프링에서는 페이징 기능을 쉽게 처리할 수 있도록 Page, Pageable, PageRequest 같은 API를 제공한다.
	/*
	@GetMapping("/user/page/{page}")
	public @ResponseBody Page<User> getUserListPaging(@PathVariable int page) {
		// page에 해당하는 2개의 데이터 조회
		// id와 username 내림차순 정렬
		Pageable pageable = PageRequest.of(page, 2, Sort.Direction.DESC, "id", "username");

		// 해당 메소드에서 URL에 포함된 페이지 번호(page)를 추출한다. 그리고 추출한 페이지 번호를 이용하여 Pageable 객체를 생성한다.
		// PageRequest.of() 메소드를 호출할 때 페이지 번호는 0부터 시작하며, 두번째 인자로 한 페이지에 출력할 데이터의 개수를
		// 지정한다.

		return userRepository.findAll(pageable);
	}
	*/
	
	// 페이징처리2
	// @PageableDefault를 이용하면 PageRequest를 사용하는 것보다 쉽게 Pageable 객체를 생성할 수 있다.
	@GetMapping("/user/page")
	public @ResponseBody Page<User> getUserListPaging(@PageableDefault(page = 0, size = 2, 
	direction = Sort.Direction.DESC, sort = {"id", "username"}) Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	
	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}
	
}
