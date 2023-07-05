package com.arsyux.thecar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.OAuthType;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.dto.UserDTO;
import com.arsyux.thecar.dto.PhoneDTO;
import com.arsyux.thecar.dto.PhoneDTO2;
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

	/*
	@GetMapping("/auth/login")
	public String login() {
		return "system/login";
	}
	*/
	// 로그인창
	@GetMapping("/auth/login")
	public String login(@RequestParam(value = "error", required = false)String error, @RequestParam(value = "exception", required = false)String exception, Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "/system/login";
	}

	// 회원가입창
	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}

	// 회원가입 로직
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {

		// 유효성 검사
		if (bindingResult.hasErrors()) {
			// 에러가 하나라도 있다면 에러 메시지를 Map에 등록
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}

		// User 객체로 변환
		User user = modelMapper.map(userDTO, User.class);
		User findUser1 = userService.getUserByPhone(user.getPhone());
		User findUser2 = userService.getUserByUsername(user.getUsername());

		if (findUser1.getPhone() != null) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("phone", "이미 가입되어있는 휴대폰 번호입니다.");
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}

		if (findUser2.getUsername() == null) {
			userService.insertUser(user);
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님, 회원가입되었습니다.");
		} else {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("username", user.getUsername() + "님은 이미 회원입니다.");
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}
	}

	// 아이디 찾기창
	@GetMapping("/auth/findUsername")
	public String findUsername() {
		return "system/findUsername";
	}

	// 아이디 찾기 로직
	@PostMapping("/auth/findUsername")
	public @ResponseBody ResponseDTO<?> findUsername(@Valid @RequestBody PhoneDTO phoneDTO,
			BindingResult bindingResult) {

		// 유효성 검사
		if (bindingResult.hasErrors()) {
			// 에러가 하나라도 있다면 에러 메시지를 Map에 등록
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}

		User user = modelMapper.map(phoneDTO, User.class);
		User findUser1 = userService.getUserByPhone(user.getPhone());

		if (findUser1.getPhone() == null) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "가입되어있는 아이디가 없습니다.");
		} else {
			return new ResponseDTO<>(HttpStatus.OK.value(), "회원님의 아이디는 [ " + findUser1.getUsername() + " ] 입니다.");
		}
	}

	// 비밀번호 찾기창
	@GetMapping("/auth/findPassword")
	public String findPassword() {
		return "system/findPassword";
	}

	// 비밀번호 찾기 로직
	@PostMapping("/auth/findPassword")
	public @ResponseBody ResponseDTO<?> findPassword(@Valid @RequestBody PhoneDTO2 phoneDTO,
			BindingResult bindingResult) {
		// 유효성 검사
		if (bindingResult.hasErrors()) {
			// 에러가 하나라도 있다면 에러 메시지를 Map에 등록
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}

		User user = modelMapper.map(phoneDTO, User.class);
		User findUser1 = userService.getUserByUsername(user.getUsername());

		if (findUser1.getUsername() == null) {
			// 아이디가 없을 경우
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "가입되어있는 아이디가 없습니다.");
		} else {
			// 아이디가 있을 경우 휴대전화번호로 찾은 아이디와 비교
			User findUser2 = userService.getUserByPhone(user.getPhone());
			if (findUser1.getUsername() == findUser2.getUsername()) {
				// 아이디로 검색한 회원과 휴대전화번호로 검색한 회원이 같을 경우
				System.out.println(findUser1);
				return new ResponseDTO<>(HttpStatus.OK.value(), findUser1);
			} else {
				// 다를 경우
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "가입된 아이디와 휴대전화번호가 일치하지 않습니다.");
			}
		}
	}

	// 비밀번호 변경 로직
	@PutMapping("/auth/updateUserPassword")
	public @ResponseBody ResponseDTO<?> updateUserPassword(@Valid @RequestBody UserDTO userDTO,
			BindingResult bindingResult) {

		// 유효성 검사
		if (bindingResult.hasErrors()) {
			System.out.println("에러 발견!");
			// 에러가 하나라도 있다면 에러 메시지를 Map에 등록
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println(error.getDefaultMessage());
			}
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}

		System.out.println("업데이트 시작!");

		// User 객체로 변환
		User user = modelMapper.map(userDTO, User.class);

		userService.updateUser(user);

		System.out.println("업데이트 완료!");

		return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님의 비밀번호가 변경되었습니다.");
	}

	// 회원정보 수정창
	@GetMapping("/user/updateUser")
	public String updateUser() {
		return "user/updateUser";
	}

	@PutMapping("/user")
	public @ResponseBody ResponseDTO<?> updateUser(@RequestBody User user,
			@AuthenticationPrincipal UserDetailsImpl principal) {
		if (principal.getUser().getOauth().equals(OAuthType.KAKAO)) {
			user.setPassword(kakaoPassword);
		} else if (principal.getUser().getOauth().equals(OAuthType.GOOGLE)) {
			user.setPassword(googlePassword);
		}

		principal.setUser(userService.updateUser(user));

		return new ResponseDTO<>(HttpStatus.OK.value(), user.getId() + " 수정 완료");
	}

	@GetMapping("/user/get/{id}")
	public @ResponseBody User getUser(@PathVariable int id) {
		User findUser2 = userRepository.findById(id).orElseThrow(() -> {
			return new TheCarException(id + "번 데이터가 없습니다.");
		});
		return findUser2;
	}

	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		return "회원 삭제 성공";
	}

	@GetMapping("/user/list")
	public @ResponseBody List<User> getUserList() {
		return userRepository.findAll();
	}

	@GetMapping("/user/page")
	public @ResponseBody Page<User> getUserListPaging(
			@PageableDefault(page = 0, size = 2, direction = Sort.Direction.DESC, sort = { "id",
					"username" }) Pageable pageable) {
		return userRepository.findAll(pageable);
	}

}