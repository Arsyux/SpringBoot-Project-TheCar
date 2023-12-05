package com.arsyux.thecar.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.arsyux.thecar.domain.FileVO;

@Mapper
public interface FileMapper {

	
	// 업로드된 파일의 정보를 DB에 저장
	@Insert("INSERT INTO tb_file(postid, original_name, save_name) "
		  + "VALUES(#{postid}, #{original_name}, #{save_name})")
	public void insertFile(FileVO files);

}