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
		return "user/insertUser";
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
	// DB의 회원정보는 수정되지만, 세션이 갱신되지 않아 화면에는 여전히 수정되기 전 회원 정보가 출력된다.
	// 수정된 회원 정보를 회원 상세화면에 출력하기 위해서는 로그아웃을 하고 다시 로그인해야 한다.
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
