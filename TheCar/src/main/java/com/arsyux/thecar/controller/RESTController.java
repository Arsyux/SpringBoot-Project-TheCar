package com.arsyux.thecar.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arsyux.thecar.domain.Users;

@RestController
public class RESTController {

	// Rest컨트롤러를 만들기 위해서는 일반 @Controller 어노테이션이 아닌
	// @RestController 어노테이션을 사용한다. @RestController 어노테이션으로 설정된 컨트롤러는 메소드가 반환한 데이터를
	// 자동으로 자바스크립트 객체 노테이션(JavaScript Object Notation, JSON) 타입으로 변환시킨다.
	
	// GET: SELECT
	//@GetMapping("/thecar")
	//public String httpGET() {
	//	return "GET 요청 처리";
	//}
	
	// 앞에서 작성한 User 객체를 반환값으로 사용할 수 있도록 httpGET() 메소드 수정
	@GetMapping("/thecar")
	public Users httpGET() {
		Users findUser = Users.builder()
				.id(1)
				.username("arsyux")
				.password("123")
				.email("arsyux@gmail.com")
				.build();
		return findUser;
	}
	
	// POST: INSERT
	@PostMapping("/thecar")
	public String httpPOST(@RequestBody User user) {
		// JSON 형태로 값 전달
		// 사용자가 전달한 정보를 JSON 형태로 변환하기 위해서는 @RequestBody 어노테이션을 사용해야 한다.
		return "POST 요청 처리 입력값 : " + user.toString();
	}
	
	// PUT: UPDATE
	@PutMapping("/thecar")
	public String httpPUT(@RequestBody User user) {
		return "PUT 요청 처리 입력 값 : " + user.toString();
	}
	
	// DELETE: DELETE
	@DeleteMapping("/thecar")
	public String httpDELETE(@RequestParam int id) {
		// 삭제 기능은 삭제할 회원의 아이디만 전달받으면 되기 때문에 @RequestBody 어노테이션이 아닌 @RequestParam 어노테이션을 사용한다.
		// 요청 URL에 ?id=1 형태로 파라미터 정보를 직접 전달한다.
		return "DELETE 요청 처리 입력 값 : " + id;
	}
	
}
