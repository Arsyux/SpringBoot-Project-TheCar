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
import org.springframework.ui.Model;
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
import com.arsyux.thecar.exception.JBlogException;
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
			return new JBlogException(id + "번 데이터가 없습니다.");
		});
		return findUser2;
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
	// @PageableDefault를 이용하면 PageRequest를 사용하는 것보다 쉽게 Pageable 객체를 생성할 수 있음.
	// @GetMapping("/user/page/{page}")
	@GetMapping("/user/page")
	public @ResponseBody Page<User> getUserListPaging(
			@PageableDefault(page = 0, size = 2, direction = Sort.Direction.DESC, sort = { "id",
					"username" }) Pageable pageable/* @PathVariable int page */) {
		// page에 해당하는 2개의 데이터 조회
		// id와 username 내림차순 정렬
		// Pageable pageable = PageRequest.of(page, 2, Sort.Direction.DESC, "id",
		// "username");
		return userRepository.findAll(pageable);
	}

	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}

	// ResponseDTO<String>이 아닌 와일드 카드인 이유는 어떤 타입의 데이터가 반환될지
	// 특정할 수 없기 때문임.
	// 지금은 회원가입 성공 문자열을 반환하지만, 경우에 따라 자바 객체나 컬렉션을 반환해야할 수 있음.
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		// UserDTO 객체에 대한 유효성 검사
		/*
		if(bindingResult.hasErrors()) {
			// 에러가 하나라도 있다면 에러 메시지를 Map에 등록
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		}
		*/

		// UserDTO -> User 객체로 변환
		User user = modelMapper.map(userDTO, User.class);
		User findUser = userService.getUser(user.getUsername());
		
		if (findUser.getUsername() == null) {
			userService.insertUser(user);
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 회원가입 성공!");
		} else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 회원입니다.");
		}
	}
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("API_KEY", kakaoId);
		return "system/login";
	}
	
	@GetMapping("/user/updateUser")
	public String updateUser() {
		return "user/updateUser";
	}
	
	@PutMapping("/user")
	public @ResponseBody ResponseDTO<?> updateUser(@RequestBody User user, @AuthenticationPrincipal UserDetailsImpl principal) {
		// 회원 정보 수정 전, 로그인에 성공한 사용자가 카카오 회원인지 확인
		if(principal.getUser().getOauth().equals(OAuthType.KAKAO)) {
			// 카카오 회원인 경우 비밀번호 고정
			user.setPassword(kakaoPassword);
		}else if(principal.getUser().getOauth().equals(OAuthType.GOOGLE)) {
			// 구글 회원인 경우 비밀번호 고정
			user.setPassword(googlePassword);
		}
		
		// 회원 정보 수정 및 세션 갱신
		principal.setUser(userService.updateUser(user));
		
		return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + " 수정 완료");
	}

}