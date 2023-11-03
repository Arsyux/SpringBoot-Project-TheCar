package com.arsyux.thecar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	//@Autowired
	//private ModelMapper modelMapper;
	
	@Value("${kakaomap_key}")
	private String kakaoMapKey;
	
	// ========================================
	// 기본 화면 설정
	// ========================================
	
	// 메인 화면
	@GetMapping({ "", "/" })
	public String getPostList(Model model) {
		// getPostList() 메소드에서 검색된 포스트 목록을 "postList"라는 이름으로 Model에 등록한다.
		// 이렇게 Model에 등록한 포스트 목록은 index.jsp 파일에서 표현식 언어(Expression Language, EL)을 통해 사용할 수 있다.
		//model.addAttribute("postList", postService.getPostList());
		
		// 카카오맵 API키 삽입
		model.addAttribute("kakaoMapKey", kakaoMapKey);
		return "index";
	}
	
	// 기본 화면 설정 - 페이징 추가
	/*
	@GetMapping({ "", "/" })
	public String getPostList(Model model, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		// @PageableDefault로 설정한 Pageable 객체는 한 화면에 3개의 포스트 데이터를 출력한다.
		// 가장 최근에 등록된 포스트부터 차례대로 출력하기 위해 id를 사용하여 내림차순 정렬을 설정한다.
		model.addAttribute("postList", postService.getPostList(pageable));
		return "index";
	}
	*/
	
	
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	

	// 세션 갱신 - 연관 매핑 수정
	// 회원 정보 수정과 관련하여 포스트 등록 기능과 댓글 등록 기능을 수정해야한다.
	// 앞에서 새로운 Post 객체나 Reply 객체를 등록할 때 연관관계에 있는 User 엔티티를 설정했다.
	// 그래야 외래키 컬럼에 USERS의 PK 값이 적절히 설정되기 때문이다.
	// 지금까지는 User 엔티티를 세션(HttpSession)으로부터 직접 추출했으나
	// 이제는 HttpSession에 저장된 SecurityContext에서 가져와야한다.
	// => HttpSession대신 @AuthenticationPrincipal 어노테이션이 설정된 UserDetailsImpl 객체를 이용하도록 수정한다.
	/*
	@PostMapping("/post/insertPost")
	public @ResponseBody ResponseDTO<?> insertPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, 
			@AuthenticationPrincipal UserDetailsImpl principal) {
	//public @ResponseBody ResponseDTO<?> insertPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, HttpSession session) {
				
		// ValidationCheckAdvice 클래스를 사용하여 통합
		//
		// PostDTO 객체에 대한 유효성 검사
		//if (bindingResult.hasErrors()) {
		//  // 에러가 하나라도 있다면 에러 메시지를 Map에 등록
		//	Map<String, String> errorMap = new HashMap<>();
		//	for(FieldError error : bindingResult.getFieldErrors()) {
		//		errorMap.put(error.getField(), error.getDefaultMessage());
		//	}
		//	return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
		//}
		
		// PostDTO -> Post 객체로 변환
		Post post = modelMapper.map(postDTO, Post.class);
		
		// Post 객체를 영속화 하기 전 연관된 User 엔티티 설정
		//User principal = (User) session.getAttribute("principal");
		post.setUser(principal.getUser());
		
		// 새로운 포스트를 등록하기 위해서는 세션에 등록된 사용자(User) 정보를 꺼내서 Post 엔티티에 설정해야 한다.
		// 그리고 PostService의 insertPost() 메소드를 호출할 때, 인자로 전달하면 된다.
		//post.setUser(principal);
		post.setCnt(0);
		
		postService.insertPost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
	}
	*/
	
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
