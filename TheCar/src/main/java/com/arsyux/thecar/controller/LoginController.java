package com.arsyux.thecar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/auth/login")
	public String login() {
		return "system/login";
	}

	// 입력한 아이디를 이용하여 회원에 대한 상세 조회를 처리한다.
	// 그리고 검색된 회원의 비밀번호를 확인하여 로그인 성공 여부를 판단한 후 최종적으로 검색된 회원 정보를 세션에 등록한다.
	@PostMapping("/auth/login")
	public @ResponseBody ResponseDTO<?> login(@RequestBody User user, HttpSession session) {
		User findUser = userService.getUser(user.getUsername());

		// 검색 결과 유무와 사용자가 입력한 비밀번호가 유효한지 확인
		if (findUser.getUsername() == null) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 아이디입니다.");
		} else {
			if (user.getPassword().equals(findUser.getPassword())) {
				// 로그인 성공 시 세션에 사용자 정보 저장
				session.setAttribute("principal", findUser);
				return new ResponseDTO<>(HttpStatus.OK.value(), findUser.getUsername() + "님 로그인에 성공하셨습니다.");
			} else {
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호 오류!");
			}
		}
	}
	
	@GetMapping("/auth/logout")
	public String logout(HttpSession session) {
		// 세션 값 청소
		session.invalidate();
		return "redirect:/";
	}

}
