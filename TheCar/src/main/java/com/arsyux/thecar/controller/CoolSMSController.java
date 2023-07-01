package com.arsyux.thecar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.dto.ResponseDTO;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Controller;

import java.util.Random;

@Controller
public class CoolSMSController {

	private DefaultMessageService messageService;

	public CoolSMSController() {
		this.messageService = NurigoApp.INSTANCE.initialize("NCSPMZ9LPKXBOSKD", "ECWEL4SUDFKVMDN3RLWEP4EWXQCDU3KO",
				"https://api.coolsms.co.kr");
	}

	@PostMapping("/auth/phoneCheck")
	public @ResponseBody ResponseDTO<?> phoneCheck(@RequestBody User user, BindingResult bindingResult) {
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
		message.setText("더카탁송 회원가입 인증번호입니다. [ " + code + " ]");

		SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
		System.out.println(response);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), code);
	}
	
}
