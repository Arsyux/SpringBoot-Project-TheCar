package com.arsyux.thecar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.arsyux.thecar.domain.FileVO;

@Mapper
public interface FileMapper {

	
	// 업로드된 파일의 정보를 DB에 저장
	@Insert("INSERT INTO tb_file(postid, original_name, save_name) "
		  + "VALUES(#{postid}, #{original_name}, #{save_name})")
	public void insertFile(FileVO files);
	
	
	@Select("SELECT f.*, p.regdate regdate "
		  + "FROM tb_file f, tb_post p "
		  + "WHERE p.postid = f.postid AND f.postid = #{postid}")
	public List<FileVO> getFileListByPostId(int postid);
	
	@Delete("DELETE FROM tb_file "
		  + "WHERE postid = #{postid}")
	public void deleteFilesByPostId(int postid);
	
}