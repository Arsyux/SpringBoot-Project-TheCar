package com.arsyux.thecar.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.domain.SearchPage;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.dto.PostDTO;
import com.arsyux.thecar.dto.PostDTO.PostValidationGroup;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.security.UserDetailsImpl;
import com.arsyux.thecar.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${kakaomap_key}")
	private String kakaoMapKey;
	
	// ========================================
	// 기본 화면 설정
	// ========================================
	
	// 메인 화면
	@GetMapping({ "", "/" })
	public String getPostList(Model model) {
		
		// 메인 포스트 정보 삽입
		//model.addAttribute("postList", postService.getPostList());
		
		// 카카오맵 API키 삽입
		model.addAttribute("kakaoMapKey", kakaoMapKey);
		
		return "index";
	}
	
	// 테스트
	@GetMapping("/test")
	public String getTest(@RequestParam(required = false, value = "start", defaultValue = "0") int start, 
						  @RequestParam(required = false, value = "size", defaultValue = "5") int size,
						  @RequestParam(required = false, value = "searchText", defaultValue = "") String searchText,
						  @AuthenticationPrincipal UserDetailsImpl principal, Model model) {

		if(!principal.getUser().getRole().equals("Admin")) {
			// 일반 회원 게시글 조회
			// -> 일반 회원은 검색 기능이 없음.
			
			// 게시글의 총 개수 조회
			int postCount = postService.getPostCount();
			
			// Page 정보 생성 시작 페이지, 크기, 총 개수
			SearchPage searchPage = new SearchPage(start, size, postCount);

			// 게시글 조회
			List<Post> postList = postService.getPostList(searchPage);
			
			// 조회된 데이터를 model에 추가
			model.addAttribute("searchPage", searchPage);
			model.addAttribute("postList", postList);
		} else {
			/*
			// 관리자용 게시글 제목내용 검색 조회
			System.out.println("관리자용");
			if(searchText.equals("")) {
				System.out.println("검색x");
				searchPage = new SearchPage(start, size);
			} else {
				System.out.println("검색o");	
				searchPage = new SearchPage(start, size, searchText);
			}
			
			model.addAttribute("page", searchPage);
			model.addAttribute("postList", postService.getTestList(searchPage));
			*/
		}
		
		return "test";
	}
		
	// 내가쓴글 조회 테스트
	@GetMapping("/test2")
	public String getTest2(@RequestParam(required = false, value = "start", defaultValue = "0") int start, 
						  @RequestParam(required = false, value = "size", defaultValue = "5") int size,
						  @RequestParam(required = false, value = "searchText", defaultValue = "") String searchText,
						  @AuthenticationPrincipal UserDetailsImpl principal, Model model) {

		// 검색할 유저 정보
		User user = principal.getUser();
		
		// 유저 이름으로 조회되는 게시글 개수 조회
		int postCount = postService.getPostCountByUsername(user);
		
		// Page 정보 생성 시작 페이지, 크기, 총 개수, 유저 이름
		SearchPage searchPage = new SearchPage(start, size, postCount, user.getName());
		
		// 게시글 조회
		List<Post> postList = postService.getPostListByUsername(searchPage);
		
		// 조회된 데이터를 model에 추가
		model.addAttribute("searchPage", searchPage);
		model.addAttribute("postList", postList);
		
		return "test";
	}
		
		
		
	
	
	
	// ========================================
	// 네비게이션바 메뉴
	// ========================================
	
	// 인사말
	@GetMapping("/info/greetings")
	public String greetings() {
		return "info/greetings";
	}
	// 회사개요
	@GetMapping("/info/outline")
	public String outline() {
		return "info/outline";
	}
	// 오시는길
	@GetMapping("/info/wayToCome")
	public String wayToCome(Model model) {
		// 카카오맵 API키 삽입
		model.addAttribute("kakaoMapKey", kakaoMapKey);
		return "info/wayToCome";
	}
	// 5톤 카캐리어
	@GetMapping("/info/fiveTonCarCarrier")
	public String fiveTonCarCarrier() {
		return "info/fiveTonCarCarrier";
	}
	// 풀카 트레일러 카캐리어
	@GetMapping("/info/fullCarTrailerCarCarrier")
	public String fullCarTrailerCarCarrier() {
		return "info/fullCarTrailerCarCarrier";
	}
	// 저상형 세이프티
	@GetMapping("/info/lowProfileSafety")
	public String lowProfileSafety() {
		return "info/lowProfileSafety";
	}
	// 고상형 세이프티
	@GetMapping("info/solidSafety")
	public String solidSafety() {
		return "info/solidSafety";
	}
	// 승용차 탁송
	@GetMapping("info/carConsignment")
	public String carConsignment() {
		return "info/carConsignment";
	}
	// 대형차 탁송
	@GetMapping("info/largeCarConsignment")
	public String largeCarConsignment() {
		return "info/largeCarConsignment";
	}
	
	
	
	
	// ========================================
	// 메인 메뉴
	// ========================================
	
	// 글쓰기 페이지로 이동
	@GetMapping("/post/insertPost")
	public String insertPost() {
		return "post/insertPost";
	}
	// 글쓰기 기능
	@PostMapping("/post/insertPost")
	public @ResponseBody ResponseDTO<?> insertPost(@Validated(PostValidationGroup.class) @RequestBody PostDTO postDTO, BindingResult bindingResult, 
			@AuthenticationPrincipal UserDetailsImpl principal) {
		
		// PostDTO -> Post로 변환
		Post post = modelMapper.map(postDTO, Post.class);
		
		// 글쓴이 설정
		post.setUid(principal.getUser().getId());
		post.setName(principal.getUser().getName());
		
		postService.insertPost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	// getPost() 메소드는 @PathVariable을 이용하여 조회할 포스트의 id 정보를 획득한다.
	// 그리고 PostService 클래스의 getPost()메소드를 호출하여 Post엔티티를 검색하고, 검색 결과를 Model에 등록한다.
	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		return "post/getPost";
	}
	
	@GetMapping("/post/updatePost/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		return "post/updatePost";
	}
	
	// Rest컨트롤러에서 수정 기능은 PUT방식으로 처리하기 때문에 @PutMapping 어노테이션을 사용한다.
	// 그 후 PostService 클래스의 updatePost() 메소드를 호출하여 포스트 수정을 처리한다.
	@PutMapping("/post")
	public @ResponseBody ResponseDTO<?> updatePost(@RequestBody Post post) {
		postService.updatePost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), post.getId() + "번 포스트를 수정했습니다.");
	}
	
	// Rest컨트롤러에서 삭제 기능은 DELETE 방식으로 처리하기 때문에 deletePost() 메소드에 @DeleteMapping을 설정한다.
	// 그 후 PostService 클래스의 deletePost() 메소드를 호출하여 포스트 삭제를 처리한다.
	@DeleteMapping("/post/{id}")
	public @ResponseBody ResponseDTO<?> deletePost(@PathVariable int id) {
		postService.deletePost(id);
		return new ResponseDTO<>(HttpStatus.OK.value(), id + "번 포스트를 삭제했습니다.");
	}
	
}
