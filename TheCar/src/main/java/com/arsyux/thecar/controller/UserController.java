package com.arsyux.thecar.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.dto.UserDTO;
import com.arsyux.thecar.security.UserDetailsImpl;
import com.arsyux.thecar.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;
	
	// 로그인 이동
	@GetMapping("/auth/loginUser")
	public String login() {
		return "user/loginUser";
	}
	
	// 회원 가입 이동
	@GetMapping("/auth/insertUser")
	public String insertUser() {
		System.out.println(5/0);
		return "user/insertUser";
	}
	// 아이디 중복 검사
	@PostMapping("/auth/insertUserCheck")
	//public @ResponseBody ResponseDTO<?> insertUserCheck(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
	public @ResponseBody ResponseDTO<?> insertUserCheck(@RequestBody User user, BindingResult bindingResult) {
		if(user.getUsername() ==  null || user.getUsername().equals("")) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "아이디가 입력되지 않았습니다.");
		}
		if(user.getUsername().length() > 100) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "올바른 값이 아닙니다.");
		}
		
		// 중복되는 아이디 검색
		User findUser = userService.findByUsername(user.getUsername());
		
		if(findUser.getUsername() == null) {
			// 중복되는 아이디가 없을 경우 중복확인 완료처리
			return new ResponseDTO<>(HttpStatus.OK.value(), "회원가입이 가능한 아이디입니다.");	
		} else {
			// 중복되는 아이디가 있을 경우 알림 표시
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "이미 사용중인 아이디입니다.");	
		}
	}
	// 회원 가입 기능
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		
		// UserDTO를 통해 유효성 검사
		User user = modelMapper.map(userDTO, User.class);
		
		// 중복되는 아이디 검색
		User findUser = userService.findByUsername(user.getUsername());
		
		if(findUser.getUsername() == null) {
			// 중복되는 아이디가 없을 경우 회원 가입 진행
			userService.insertUser(user);
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 환영합니다!");		
		} else {
			// 중복되는 아이디가 있을 경우 알림 표시
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 회원가입 되어있습니다.");	
		}
	}
	
	
	
	@GetMapping("/user/updateUser")
	public String updateUser() {
		return "user/updateUser";
	}
	
	
	// 회원 정보를 수정하는 updateUser()메소드 작성
	@PutMapping("/user")
	public @ResponseBody ResponseDTO<?> updateUser(@RequestBody User user, @AuthenticationPrincipal UserDetailsImpl principal) {

		//userService.updateUser(user);
		
		// 회원 정보 수정과 동시에 세션 갱신
		principal.setUser(userService.updateUser(user));
		
		// 기존의 updateUser() 메소드는 회원 정보 수정만 처리했었다.
		// 회원 정보 수정과 동시에 세션을 갱신해야하므로 updateUser()메소드에서 회원 정보를 수정하고 UserService.updateUser() 메소드가 반환한 User 객체로
		// SecurityContext에 등록된 AUthentication 객체의 User를 변경하도록 한다.
		
		return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + " 수정 완료");
	}
	
}
