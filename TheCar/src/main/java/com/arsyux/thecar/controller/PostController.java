package com.arsyux.thecar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	// 기본 화면 설정
	@GetMapping({ "", "/" })
	public String getPostList() {
		return "index";
	}

	@GetMapping("/post/insertPost")
	public String insertPost() {
		return "post/insertPost";
	}

	@PostMapping("/post/insertPost")
	public @ResponseBody ResponseDTO<?> insertPost(@RequestBody Post post, HttpSession session) {
		// Post 객체를 영속화 하기 전 연관된 User 엔티티 설정
		User principal = (User) session.getAttribute("principal");
		// 새로운 포스트를 등록하기 위해서는 세션에 등록된 사용자(User) 정보를 꺼내서 Post 엔티티에 설정해야 한다.
		// 그리고 PostService의 insertPost() 메소드를 호출할 때, 인자로 전달하면 된다.
		post.setUser(principal);
		post.setCnt(0);
		
		postService.insertPost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
	}

}
