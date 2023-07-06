package com.arsyux.thecar.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.dto.PhoneDTO;
import com.arsyux.thecar.dto.PhoneDTO2;
import com.arsyux.thecar.dto.ResponseDTO;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

@Controller
public class CoolSMSController {

	private DefaultMessageService messageService;

	@Autowired
	private ModelMapper modelMapper;

	public CoolSMSController() {
		this.messageService = NurigoApp.INSTANCE.initialize("NCSPMZ9LPKXBOSKD", "ECWEL4SUDFKVMDN3RLWEP4EWXQCDU3KO",
				"https://api.coolsms.co.kr");
	}

	// 회원가입 인증, 아이디 인증
	@PostMapping("/auth/phoneCheck")
	public @ResponseBody ResponseDTO<?> phoneCheck(@Valid @RequestBody PhoneDTO phoneDTO, BindingResult bindingResult) {

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

		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
		message.setFrom("01094436580");
		message.setTo(user.getPhone());

		// 코드생성
		String code = "";
		for (int i = 0; i < 6; i++) {
			if (new Random().nextInt(10) % 2 == 0) {
				code += (char) (new Random().nextInt(26) + 65);
			} else {
				code += new Random().nextInt(10);
			}
		}
		message.setText("더카탁송 인증번호입니다. [ " + code + " ]");

		// DefaultMessageService messageService =
		// NurigoApp.INSTANCE.initialize("NCSPMZ9LPKXBOSKD",
		// "ECWEL4SUDFKVMDN3RLWEP4EWXQCDU3KO", "https://api.coolsms.co.kr");
		// SingleMessageSentResponse response = messageService.sendOne(new
		// SingleMessageSendingRequest(message));

		SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
		System.out.println(response);

		return new ResponseDTO<>(HttpStatus.OK.value(), code);
	}

	// 비밀번호 찾기 인증
	@PostMapping("/auth/phoneCheck2")
	public @ResponseBody ResponseDTO<?> phoneCheck2(@Valid @RequestBody PhoneDTO2 userDTO,
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

		User user = modelMapper.map(userDTO, User.class);

		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
		message.setFrom("01094436580");
		message.setTo(user.getPhone());

		// 코드생성
		String code = "";
		for (int i = 0; i < 6; i++) {
			if (new Random().nextInt(10) % 2 == 0) {
				code += (char) (new Random().nextInt(26) + 65);
			} else {
				code += new Random().nextInt(10);
			}
		}
		message.setText("더카탁송 인증번호입니다. [ " + code + " ]");

		//DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSPMZ9LPKXBOSKD",
		//		"ECWEL4SUDFKVMDN3RLWEP4EWXQCDU3KO", "https://api.coolsms.co.kr");
		//SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
		
		SingleMessageSentResponse response = this.messageService.sendOne(new
		SingleMessageSendingRequest(message));
		System.out.println(response);

		return new ResponseDTO<>(HttpStatus.OK.value(), code);
	}
}
