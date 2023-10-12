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
	// pk는 AUTO_INCREMENT처리
	// regdate는 CURRENT_TIMESTAMP 처리
	@Insert("INSERT INTO USER(USERNAME, PASSWORD, NAME, BIRTH, GENDER, PHONE, EMAIL) "
			+ "VALUES(#{username}, #{password}, #{name}, #{birth}, #{gender}, #{phone}, #{email})")
	public void insertUser(User user);
		
	// 로그인 아이디로 회원 1명 조회
	@Select("SELECT * FROM USER WHERE USERNAME = #{username}")
	public User findByUsername(String username);
	
	// 휴대폰으로 회원 1명 조회
	@Select("SELECT * FROM USER WHERE PHONE = #{phone}")
	public User findByPhone(String phone);
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Select("SELECT * FROM USER ORDER BY USERNAME DESC")
	public List<User> getUserList();

	

	@Update("UPDATE USER SET PASSWORD = #{password} WHERE ID = #{id}")
	public void updateUser(User user);

	@Delete("DELETE USER WHERE ID = #{id}")
	public void deleteUser(User user);

}
