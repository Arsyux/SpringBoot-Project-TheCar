package com.arsyux.thecar.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.arsyux.thecar.domain.FileUtils;
import com.arsyux.thecar.domain.FileVO;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	
	@Autowired
	private FileUtils fileUtils;
	
	// 파일 업로드 기능
	@PostMapping("/file/insertFiles")
	public @ResponseBody ResponseDTO<?> insertFiles(@Param("postid") int postid, @Param("files") List<MultipartFile> files) {
		
		System.out.println(postid);
		
		for (MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());
		}
		
		List<FileVO> filesList = fileUtils.uploadFiles(files);
		
		if(filesList == null ) {
			System.out.println("filesList가 null임");
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "업로드 오류");
		}
		
		fileService.insertFiles(postid, filesList);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
	}
	
}
