package com.arsyux.thecar.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.dto.UpdateUserDTO;
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
	public String login(@RequestParam(value = "error", required = false)String error,
						@RequestParam(value = "exception", required = false)String exception,
						Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
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
		User findUsername = userService.findByUsername(user.getUsername());
		
		if(findUsername.getUsername() == null) {
			// 중복되는 아이디가 없을 경우
			User findUserPhone = userService.findByPhone(user.getPhone());
			
			if(findUserPhone.getPhone() == null) {
				// 중복되는 아이디가 없고, 가입된 휴대폰이 없을 경우 회원 가입 진행
				userService.insertUser(user);
				return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 환영합니다!");		
			} else {
				// 가입된 휴대폰이 있을 경우
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "해당 휴대폰으로 이미 가입된 아이디가 있습니다.");
			}
		} else {
			// 중복되는 아이디가 있을 경우 알림 표시
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 회원가입 되어있습니다.");	
		}
		
	}
	
	// 회원 정보 수정 이동
	@GetMapping("/user/updateUser")
	public String updateUser() {
		return "user/updateUser";
	}
	// 회원 정보 수정
	@PutMapping("/user/updateUser")
	public @ResponseBody ResponseDTO<?> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO, @AuthenticationPrincipal UserDetailsImpl principal) {

		// UpdateUserDTO를 통해 유효성 검사
		User user = modelMapper.map(updateUserDTO, User.class);
		
		// 회원 정보 수정과 동시에 세션을 갱신해야하므로 updateUser()메소드에서 회원 정보를 수정하고 UserService.updateUser() 메소드가 반환한 User 객체로
		// SecurityContext에 등록된 AUthentication 객체의 User를 변경하도록 한다.
		principal.setUser(userService.updateUser(user));
		
		return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님의 정보가 수정되었습니다.");
	}
	
}
