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

import com.arsyux.thecar.domain.FileUtils;
import com.arsyux.thecar.domain.FileVO;
import com.arsyux.thecar.domain.PostVO;
import com.arsyux.thecar.domain.PageUtils;
import com.arsyux.thecar.domain.UserVO;
import com.arsyux.thecar.dto.PostDTO;
import com.arsyux.thecar.dto.PostDTO.PostValidationGroup;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.security.UserDetailsImpl;
import com.arsyux.thecar.service.FileService;
import com.arsyux.thecar.service.PostService;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	
	private FileUtils fileUtils;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 파일 업로드 기능
	@PostMapping("/post/insertFiles")
	public @ResponseBody ResponseDTO<?> insertFiles(@Validated(PostValidationGroup.class) @RequestBody PostDTO postDTO, BindingResult bindingResult, 
			@AuthenticationPrincipal UserDetailsImpl principal) {
		
		// PostDTO -> Post로 변환
		PostVO post = modelMapper.map(postDTO, PostVO.class);
		
		// 글쓴이 설정
		post.setUserid(principal.getUser().getUserid());
		post.setName(principal.getUser().getName());
		
		// 게시글 inert
		postService.insertPost(post);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
	}
	
}
