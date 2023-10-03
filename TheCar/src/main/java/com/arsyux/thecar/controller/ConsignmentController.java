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

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.dto.PostDTO;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.security.UserDetailsImpl;

@Controller
public class ConsignmentController {
	
	// 탁송 예약 GetMapping
	@GetMapping("/consignment/insertConsignment")
	public String reservationConsignment() {
		return "consignment/reservationConsignment";
	}
	
	// 탁송 예약 PostMapping
	//@PostMapping("/consignment/reservationConsignment")
	@PostMapping("/post/insertConsignment")
	public @ResponseBody ResponseDTO<?> reservationConsignment(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, 
			@AuthenticationPrincipal UserDetailsImpl principal) {
		
		// ConsignmentDTO -> Consignment 객체로 변환
		Post post = modelMapper.map(postDTO, Post.class);
		
		// 새로운 포스트를 등록하기 위해서는 세션에 등록된 사용자(User) 정보를 꺼내서 Post 엔티티에 설정해야 한다.
		// 그리고 PostService의 insertPost() 메소드를 호출할 때, 인자로 전달하면 된다.
		//post.setUser(principal);
		post.setCnt(0);
		
		postService.insertPost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
	}
	
	
}
