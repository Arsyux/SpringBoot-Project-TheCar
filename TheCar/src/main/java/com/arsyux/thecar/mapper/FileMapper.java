package com.arsyux.thecar.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.arsyux.thecar.domain.FileVO;

@Mapper
public interface FileMapper {

	/**
	 * 업로드된 파일의 정보를 DB에 저장
	 * @param files - 파일 정보 리스트
	 */
	@Insert("INSERT INTO tb_file(postid, original_name, save_name, size, delete_yn) "
		  + "VALUES(#{postid}, #{original_name}, #{save_name}, #{size}, 0)")
	public void insertFile(FileVO files);

}