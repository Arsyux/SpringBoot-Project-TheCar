package com.arsyux.thecar.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.OAuthType;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.dto.UserDTO;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.exception.TheCarException;
import com.arsyux.thecar.persistence.UserRepository;
import com.arsyux.thecar.security.UserDetailsImpl;
import com.arsyux.thecar.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;


	@Value("${kakao.default.id}")
	private String kakaoId;
	
	@Value("${kakao.default.password}")
	private String kakaoPassword;

	@Value("${google.default.password}")
	private String googlePassword;
	
	@GetMapping("/user/get/{id}")
	public @ResponseBody User getUser(@PathVariable int id) {
		User findUser2 = userRepository.findById(id).orElseThrow(() -> {
			return new TheCarException(id + "번 데이터가 없습니다.");
		});
		return findUser2;
	}
	
	// 회원가입창
	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}
	// 회원가입 로직
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		
		System.out.println(userDTO);
		// 유효성 검사
		User user = modelMapper.map(userDTO, User.class);
		User findUser = userService.getUser(user.getUsername());
		
		if (findUser.getUsername() == null) {
			userService.insertUser(user);
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님, 회원가입되었습니다.");
		} else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 회원입니다.");
		}
	}
	
	
	
	
	
	
	
	
	
	// 회원 삭제
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		return "회원 삭제 성공";
	}

	// 회원 목록 검색
	@GetMapping("/user/list")
	public @ResponseBody List<User> getUserList() {
		return userRepository.findAll();
	}

	// 페이징 처리
	@GetMapping("/user/page")
	public @ResponseBody Page<User> getUserListPaging(
			@PageableDefault(page = 0, size = 2, direction = Sort.Direction.DESC, sort = { "id",
					"username" }) Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	

	
	
	@GetMapping("/auth/login")
	public String login() {
		return "system/login";
	}
	
	@GetMapping("/user/updateUser")
	public String updateUser() {
		return "user/updateUser";
	}
	
	@PutMapping("/user")
	public @ResponseBody ResponseDTO<?> updateUser(@RequestBody User user, @AuthenticationPrincipal UserDetailsImpl principal) {
		if(principal.getUser().getOauth().equals(OAuthType.KAKAO)) {
			user.setPassword(kakaoPassword);
		}else if(principal.getUser().getOauth().equals(OAuthType.GOOGLE)) {
			user.setPassword(googlePassword);
		}
		
		principal.setUser(userService.updateUser(user));
		
		return new ResponseDTO<>(HttpStatus.OK.value(), user.getId() + " 수정 완료");
	}

}