package com.arsyux.thecar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	/*
	@GetMapping({ "", "/" })
	public String getPostList(Model model) {
		// getPostList() 메소드에서 검색된 포스트 목록을 "postList"라는 이름으로 Model에 등록한다.
		// 이렇게 Model에 등록한 포스트 목록은 index.jsp 파일에서 표현식 언어(Expression Language, EL)을 통해 사용할 수 있다.
		model.addAttribute("postList", postService.getPostList());
		return "index";
	}
	*/
	
	// 기본 화면 설정 - 페이징 추가
	@GetMapping({ "", "/" })
	public String getPostList(Model model, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		// @PageableDefault로 설정한 Pageable 객체는 한 화면에 3개의 포스트 데이터를 출력한다.
		// 가장 최근에 등록된 포스트부터 차례대로 출력하기 위해 id를 사용하여 내림차순 정렬을 설정한다.
		model.addAttribute("postList", postService.getPostList(pageable));
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
