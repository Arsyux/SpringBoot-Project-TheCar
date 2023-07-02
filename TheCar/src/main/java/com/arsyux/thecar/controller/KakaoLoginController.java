package com.arsyux.thecar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.service.KakaoLoginService;
import com.arsyux.thecar.service.UserService;

@Controller
public class KakaoLoginController {

	@Autowired
	private KakaoLoginService kakaoLoginService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${kakao.default.password}")
	private String kakaoPassword;

	// @ResponseBody를 제거해야 인덱스 페이지로의 전환이 가능하다.
	@GetMapping("/oauth/kakao")
	public String kakaoCallback(String code) {
		// 1. 인증 서버로부터 받은 CODE를 이용하여 액세스 토큰을 얻는다.
		String accessToken = kakaoLoginService.getAccessToken(code);

		// 2. 액세스 토큰을 이용하여 사용자 정보를 얻어온다.
		User kakaoUser = kakaoLoginService.getUserInfo(accessToken);

		System.out.println("kakaoCallback: " + kakaoUser);
		
		// 3. 기존회원이 아니면 신규회원으로 등록한다.
		User findUser = userService.getUserByUsername(kakaoUser.getUsername());
		if (findUser.getUsername() == null) {
			userService.insertUser(kakaoUser);
		}

		// 4. 카카오로 받은 사용자 정보를 기반으로 인증을 처리한다.
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				kakaoUser.getId(), kakaoPassword);
		
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}

}
