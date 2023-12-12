package com.arsyux.thecar.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.arsyux.thecar.domain.FileUtils;
import com.arsyux.thecar.domain.FileVO;
import com.arsyux.thecar.domain.PostVO;
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
		
		List<FileVO> filesList = fileUtils.uploadFiles(files);
		
		if(filesList == null ) { return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "업로드 오류"); }
		
		fileService.insertFiles(postid, filesList);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "게시글을 등록했습니다.");
	}
	
	// 파일 삭제 기능
	@DeleteMapping("/file/deleteFiles")
	public @ResponseBody ResponseDTO<?> deleteFiles(@RequestBody PostVO post) {
		
		// 삭제할 tb_file의 pk를 가져옴
		List<FileVO> fileList = fileService.getFileListByPostId(post.getPostid());
		
		if(fileList != null) {
			try { fileUtils.deleteFiles(fileList); }
			catch (Exception e) { return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "삭제 오류"); }
			
			fileService.deleteFilesByPostId(post.getPostid());
		}
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "게시글을 등록했습니다.");
	}
	
}
