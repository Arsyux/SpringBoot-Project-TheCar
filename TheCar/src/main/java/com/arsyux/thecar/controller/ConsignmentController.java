package com.arsyux.thecar.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.dto.PostDTO;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.security.UserDetailsImpl;

@Controller
public class ConsignmentController {
	
	// 탁송 예약 GetMapping
	@GetMapping("/consignment/insertConsignment")
	public String reservationConsignment() {
		return "consignment/insertConsignment";
	}
	
	// 탁송 예약 PostMapping
	@PostMapping("/post/insertConsignment")
	public @ResponseBody ResponseDTO<?> reservationConsignment(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, 
			@AuthenticationPrincipal UserDetailsImpl principal) {
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
	}
	
	
}
