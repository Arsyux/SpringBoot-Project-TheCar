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
import com.arsyux.thecar.domain.FileVO;
import com.arsyux.thecar.domain.PageUtils;
import com.arsyux.thecar.domain.UserVO;
import com.arsyux.thecar.dto.PostDTO;
import com.arsyux.thecar.dto.PostDTO.PostProgressValidationGroup;
import com.arsyux.thecar.dto.PostDTO.PostValidationGroup;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.security.UserDetailsImpl;
import com.arsyux.thecar.service.FileService;
import com.arsyux.thecar.service.PostService;
import com.arsyux.thecar.service.UserService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private FileService fileService;
	
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
			  @RequestParam(required = false, value = "searchType", defaultValue = "") String searchType,
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
			
			if(searchType.equals("") || searchType.equals("")) {
				// 일반 게시글 조회
				
				// 게시글의 총 개수 조회
				int postCount = postService.getPostCount();
				
				// Page 정보 생성 시작 페이지, 총 개수
				PageUtils searchPage = new PageUtils(start, postCount);
	
				// 게시글 조회
				List<PostVO> postList = postService.getPostList(searchPage);

				// 조회된 데이터를 model에 추가
				model.addAttribute("searchPage", searchPage);
				model.addAttribute("postList", postList);
			} else {
				int postCount = 0;
				PageUtils searchPage = null;
				List<PostVO> postList = null;
				PostVO post = new PostVO();

				switch (searchType) {
					case "title":
						// 제목 검색
						post.setTitle(searchText);
						
						postCount = postService.getPostCountByTitle(post);
						searchPage = new PageUtils(start, postCount);
						searchPage.setSearchTitle(searchText);
						
						postList = postService.getPostListByTitle(searchPage);
						model.addAttribute("searchType", "title");
						model.addAttribute("searchText", searchText);
						break;
					case "titlecontent":
						// 제목내용 검색
						post.setTitle(searchText);
						post.setContent(searchText);
						
						postCount = postService.getPostCountByTitleContent(post);
						searchPage = new PageUtils(start, postCount);
						searchPage.setSearchTitle(searchText);
						searchPage.setSearchContent(searchText);
						
						postList = postService.getPostListByTitleContent(searchPage);
						model.addAttribute("searchType", "titlecontent");
						model.addAttribute("searchText", searchText);
						break;
					case "name":
						// 작성자 검색
						post.setName(searchText);
						
						postCount = postService.getPostCountByLikeName(post);
						searchPage = new PageUtils(start, postCount);
						searchPage.setSearchName(searchText);
						
						postList = postService.getPostListByLikeName(searchPage);
						model.addAttribute("searchType", "name");
						model.addAttribute("searchText", searchText);
						break;
					default:
						// 일반 검색
						postCount = postService.getPostCount();
						searchPage = new PageUtils(start, postCount);
						
						postList = postService.getPostList(searchPage);
						break;
				}
				
				
				model.addAttribute("searchPage", searchPage);
				model.addAttribute("postList", postList);
			}
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
		List<PostVO> postList = postService.getPostListByName(searchPage);
		
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
			
			List<FileVO> files = fileService.getFileListByPostId(id);
			//for(FileVO file : files) {
			//	file.setSave_name(fileUtils.getUploadPath() + File.separator + file.getSave_name());
			//}
			post.setFiles(files);
			
			
			// 글의 작성자 혹은 관리자만 확인가능
			model.addAttribute("post", post);
		}
		
		return "post/getPost";
	}
	
	// 글 삭제 기능
	@DeleteMapping("/post/{postid}")
	public @ResponseBody ResponseDTO<?> deletePost(@RequestBody PostVO post, @AuthenticationPrincipal UserDetailsImpl principal) {
		
		if(principal == null) { new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."); }
		
		UserVO user = principal.getUser();
		
		post.setUserid(user.getUserid());
		
		postService.deletePost(post);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), post.getPostid() + "번 게시글을 삭제했습니다.");
	}
	
	// 게시글 수정 이동
	@GetMapping("/post/updatePost/{postid}")
	public String updatePost(@PathVariable int postid, Model model) {
		
		PostVO post = postService.getPostByPostId(postid);
		
		List<FileVO> files = fileService.getFileListByPostId(postid);
		
		post.setFiles(files);
		
		model.addAttribute("post", post);
		
		return "post/updatePost";
	}
	
	// 게시글 수정
	@PutMapping("/post/updatePost")
	public @ResponseBody ResponseDTO<?> updatePost(@Validated(PostValidationGroup.class) @RequestBody PostDTO postDTO, BindingResult bindingResult) {
		
		// PostDTO -> Post로 변환
		PostVO post = modelMapper.map(postDTO, PostVO.class);
		
		postService.updatePost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), post.getPostid() + "번 게시글을 수정했습니다.");
	}
	
	// 게시물 진행
	@PutMapping("/post/progressPost")
	public @ResponseBody ResponseDTO<?> progressPost(@Validated(PostProgressValidationGroup.class) @RequestBody PostDTO postDTO, BindingResult bindingResult, 
													 @AuthenticationPrincipal UserDetailsImpl principal) {
		
		if(!principal.getUser().getRole().equals("Admin")) { return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "잘못된 접근입니다."); }
		
		// PostDTO -> Post로 변환
		PostVO post = modelMapper.map(postDTO, PostVO.class);
		
		// 가격 설정 및 게시글 상태를 '진행(P)'으로 변경
		postService.progressPost(post);
		return new ResponseDTO<>(HttpStatus.OK.value(), post.getPostid() + "번 게시글을 진행처리하였습니다.");
	}
	
	// 게시물 완료
	@PutMapping("/post/completePost")
	public @ResponseBody ResponseDTO<?> completePost(@RequestBody PostVO post, @AuthenticationPrincipal UserDetailsImpl principal) {
		
		if(!principal.getUser().getRole().equals("Admin")) { return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "잘못된 접근입니다."); }
		
		// 게시글을 작성한 유저를 찾음
		UserVO user = userService.findByPostId(post.getPostid());
		
		// 게시글의 포인트를 가져옴
		PostVO findpost = postService.getPostByPostId(post.getPostid());
		
		// 게시글의 가격의 10%만큼 포인트 설정
		user.setPoint(user.getPoint() + (int)(findpost.getPrice()/10));
		
		// 포인트 적립
		userService.updatePoint(user);
		
		// 게시글 상태를 '완료(C)'로 변경
		postService.completePost(post);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), post.getPostid() + "번 게시글을 완료처리하였습니다.");
	}
}
