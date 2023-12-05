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

import com.arsyux.thecar.domain.PostVO;
import com.arsyux.thecar.domain.PageUtils;
import com.arsyux.thecar.domain.UserVO;
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
	public String getPostList(@RequestParam(required = false, value = "start", defaultValue = "0") int start,
			  @RequestParam(required = false, value = "searchText", defaultValue = "") String searchText,
			  @AuthenticationPrincipal UserDetailsImpl principal, Model model) {
		
		if(principal == null) {
			// 비회원 게시글 조회
			
			// 게시글의 총 개수 조회
			int postCount = postService.getPostCount();
			
			// Page 정보 생성 시작 페이지, 총 개수
			PageUtils searchPage = new PageUtils(start, postCount);

			// 게시글 조회
			List<PostVO> postList = postService.getPostList(searchPage);
			
			// 작성자 이름 가리기
			for(int i=0; i<postList.size(); i++) {
				String name = "";
				for(int j=0; j<postList.get(i).getName().length(); j++) {
					if(j > (int)(postList.get(i).getName().length() / 2)) {
						name += "*";
					} else {
						name += postList.get(i).getName().toCharArray()[j];						
					}
				}
				postList.get(i).setName(name);
			}
			
			// 조회된 데이터를 model에 추가
			model.addAttribute("searchPage", searchPage);
			model.addAttribute("postList", postList);
		} else if(!principal.getUser().getRole().equals("Admin")) {
			// 일반 회원 게시글 조회
			
			// 게시글의 총 개수 조회
			int postCount = postService.getPostCount();
			
			// Page 정보 생성 시작 페이지, 총 개수
			PageUtils searchPage = new PageUtils(start, postCount);

			// 게시글 조회
			List<PostVO> postList = postService.getPostList(searchPage);
			
			// 작성자 이름 가리기
			for(int i=0; i<postList.size(); i++) {
				String name = "";
				for(int j=0; j<postList.get(i).getName().length(); j++) {
					if(j > (int)(postList.get(i).getName().length() / 2)) {
						name += "*";
					} else {
						name += postList.get(i).getName().toCharArray()[j];						
					}
				}
				postList.get(i).setName(name);
			}
						
			// 조회된 데이터를 model에 추가
			model.addAttribute("searchPage", searchPage);
			model.addAttribute("postList", postList);
		} else {
			// 관리자용 게시글 일반 및 제목내용 검색 조회
			
			// 게시글의 총 개수 조회
			int postCount = postService.getPostCount();
			
			// Page 정보 생성 시작 페이지, 총 개수
			PageUtils searchPage = new PageUtils(start, postCount);

			// 게시글 조회
			List<PostVO> postList = postService.getPostList(searchPage);
			
			// 작성자 이름 가리기
			for(int i=0; i<postList.size(); i++) {
				String name = "";
				for(int j=0; j<postList.get(i).getName().length(); j++) {
					if(j > (int)(postList.get(i).getName().length() / 2)) {
						name += "*";
					} else {
						name += postList.get(i).getName().toCharArray()[j];						
					}
				}
				postList.get(i).setName(name);
			}
						
			// 조회된 데이터를 model에 추가
			model.addAttribute("searchPage", searchPage);
			model.addAttribute("postList", postList);
		}
		
		// 카카오맵 API키 삽입
		model.addAttribute("kakaoMapKey", kakaoMapKey);
		
		return "index";
	}
	
	// 내가쓴글 조회
	@GetMapping("/post/mypost")
	public String getMyPost(@RequestParam(required = false, value = "start", defaultValue = "0") int start,
						  @RequestParam(required = false, value = "searchText", defaultValue = "") String searchText,
						  @AuthenticationPrincipal UserDetailsImpl principal, Model model) {
		// 검색할 유저 정보
		UserVO user = principal.getUser();
		
		// 유저번호로 조회되는 게시글 개수 조회
		int postCount = postService.getPostCountByUserid(user);
		
		// Page 정보 생성 시작 페이지, 크기, 총 개수, 유저 이름
		PageUtils searchPage = new PageUtils(start, postCount, user.getUsername());
		
		// 게시글 조회
		List<PostVO> postList = postService.getPostListByUsername(searchPage);
		
		// 작성자 이름 가리기
		for(int i=0; i<postList.size(); i++) {
			String name = "";
			for(int j=0; j<postList.get(i).getName().length(); j++) {
				if(j > (int)(postList.get(i).getName().length() / 2)) {
					name += "*";
				} else {
					name += postList.get(i).getName().toCharArray()[j];						
				}
			}
			postList.get(i).setName(name);
		}
		
		// 조회된 데이터를 model에 추가
		model.addAttribute("searchPage", searchPage);
		model.addAttribute("postList", postList);
		
		return "index";
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
		
		if(principal.getUser() == null) { return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "회원 정보가 없습니다."); }
		
		// PostDTO -> Post로 변환
		PostVO post = modelMapper.map(postDTO, PostVO.class);
		
		UserVO user = principal.getUser();
		
		// 글쓴이 설정
		post.setUserid(principal.getUser().getUserid());
		
		// 게시글 inert
		postService.insertPost(post);
		
		// postid 가져오기
		// 해당 유저가 작성한 게시글중 가장 최신 게시글정보를 가져옴
		PostVO findPost = postService.getLastPostByUserid(user);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), findPost.getPostid());
	}
	// 글조회 이동
	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model, @AuthenticationPrincipal UserDetailsImpl principal) {
		
		PostVO post = postService.getPostByPostId(id);
		
		if(post == null || principal == null) { return "/"; }
		
		UserVO user = principal.getUser();
		
		if(user.getRole().equals("Admin") || post.getUserid() == user.getUserid()) {
			// 작성자 이름 가리기
			String name = "";
			for(int j=0; j<post.getName().length(); j++) {
				if(j > (int)(post.getName().length() / 2)) {
					name += "*";
				} else {
					name += post.getName().toCharArray()[j];						
				}
			}
			post.setName(name);
			
			// 글의 작성자 혹은 관리자만 확인가능
			model.addAttribute("post", post);
		}
		
		return "post/getPost";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	@GetMapping("/post/updatePost/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		//model.addAttribute("post", postService.getPost(id));
		return "post/updatePost";
	}
	
	// Rest컨트롤러에서 수정 기능은 PUT방식으로 처리하기 때문에 @PutMapping 어노테이션을 사용한다.
	// 그 후 PostService 클래스의 updatePost() 메소드를 호출하여 포스트 수정을 처리한다.
	@PutMapping("/post")
	public @ResponseBody ResponseDTO<?> updatePost(@RequestBody PostVO post) {
		//postService.updatePost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), post.getPostid() + "번 포스트를 수정했습니다.");
	}
	
	// Rest컨트롤러에서 삭제 기능은 DELETE 방식으로 처리하기 때문에 deletePost() 메소드에 @DeleteMapping을 설정한다.
	// 그 후 PostService 클래스의 deletePost() 메소드를 호출하여 포스트 삭제를 처리한다.
	@DeleteMapping("/post/{id}")
	public @ResponseBody ResponseDTO<?> deletePost(@PathVariable int id) {
		//postService.deletePost(id);
		return new ResponseDTO<>(HttpStatus.OK.value(), id + "번 포스트를 삭제했습니다.");
	}
	
}
