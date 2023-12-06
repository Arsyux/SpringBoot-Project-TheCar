package com.arsyux.thecar.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.FileVO;

@Repository
public class FileDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 파일 업로드
	public void insertFiles(List<FileVO> files) { 
		for (FileVO file : files) {			
			mybatis.insert("insertFile", file);
		}
	}
	
	public List<FileVO> getFileListByPostId(int postid) {
		return mybatis.selectList("getFileListByPostId", postid);
	}

	public void deleteFilesByPostId(int postid) {
		mybatis.delete("deleteFilesByPostId", postid);
	}
}
