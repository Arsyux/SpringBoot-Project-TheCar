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

	// ========================================
	// 1. 회원가입
	// ========================================
	
	// 회원가입
	@Insert("INSERT INTO USER(USERNAME, PASSWORD, NAME, BIRTH, GENDER, PHONE, EMAIL) "
			+ "VALUES(#{username}, #{password}, #{name}, #{birth}, #{gender}, #{phone}, #{email})")
	public void insertUser(User user);
	
	// ========================================
	// 2. 회원 검색
	// ========================================
	// 회원 번호로 회원 1명 조회
	@Select("SELECT * FROM USER WHERE USERID = #{userid}")
	public User findById(int userid);
	
	// 로그인 아이디로 회원 1명 조회
	@Select("SELECT * FROM USER WHERE USERNAME = #{username}")
	public User findByUsername(String username);
	
	// 휴대폰으로 회원 1명 조회
	@Select("SELECT * FROM USER WHERE PHONE = #{phone}")
	public User findByPhone(String phone);
	
	// 휴대폰으로 회원 1명 조회
	@Select("SELECT * FROM USER WHERE EMAIL = #{email}")
	public User findByEmail(String email);
	
	// 아이디 찾기
	@Select("SELECT * FROM USER WHERE NAME = #{name} AND PHONE = #{phone} AND EMAIL = #{email}")
	public User findUsername(User user);
	
	// 비밀번호 찾기
	@Select("SELECT * FROM USER WHERE USERNAME = #{username} AND NAME = #{name} AND PHONE = #{phone} AND EMAIL = #{email}")
	public User findPassword(User user);
	
	
	
	
	
	
	
	
	
	@Select("SELECT * FROM USER ORDER BY USERNAME DESC")
	public List<User> getUserList();

	

	@Update("UPDATE USER SET PASSWORD = #{password} WHERE USERID = #{userid}")
	public void updateUser(User user);
	
	@Update("UPDATE USER SET PASSWORD = #{password} WHERE USERID = #{userid}")
	public void changePassword(User user);

	@Delete("DELETE USER WHERE USERID = #{userid}")
	public void deleteUser(User user);

}
