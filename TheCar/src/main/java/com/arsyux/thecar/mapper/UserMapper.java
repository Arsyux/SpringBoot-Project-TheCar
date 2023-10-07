package com.arsyux.thecar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arsyux.thecar.domain.User;

@Mapper
public interface UserMapper {

	// 회원 가입
	// PK는 AUTO_INCREMENT처리
	@Insert("INSERT INTO USER(USERNAME, PASSWORD) VALUES(#{username}, #{password})")
	public void insertUser(User user);
		
	// 회원 1명 조회
	@Select("SELECT * FROM USER WHERE USERNAME = #{username}")
	public User getUser(String username);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Select("SELECT * FROM USER ORDER BY USERNAME DESC")
	public List<User> getUserList();

	

	@Update("UPDATE USER SET PASSWORD = #{password} WHERE ID = #{id}")
	public void updateUser(User user);

	@Delete("DELETE USER WHERE ID = #{id}")
	public void deleteUser(User user);

}
