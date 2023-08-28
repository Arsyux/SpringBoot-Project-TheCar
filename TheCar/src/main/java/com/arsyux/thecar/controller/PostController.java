package com.arsyux.thecar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

	// 기본 화면 설정
	@GetMapping({"", "/"})
	public String getPostList() {
		return "index";
	}

	@GetMapping("/post/insertPost")
	public String insertPost() {
		return "post/insertPost";
	}
	
}
