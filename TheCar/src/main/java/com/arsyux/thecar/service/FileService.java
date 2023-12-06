package com.arsyux.thecar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.FileVO;
import com.arsyux.thecar.persistence.FileDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	@Autowired
	private FileDAO fileDAO;
	
	@Transactional
	public void insertFiles(int postid, List<FileVO> files) { 
		
		if(files == null) { return; }
		
		for (FileVO file : files) { file.setPostId(postid); }
		
		fileDAO.insertFiles(files);
	}
	
	@Transactional
	public List<FileVO> getFileListByPostId(int postid) {
		return fileDAO.getFileListByPostId(postid);
	}
	
	@Transactional
	public void deleteFilesByPostId(int postid) {
		fileDAO.deleteFilesByPostId(postid);
	}
	
}
