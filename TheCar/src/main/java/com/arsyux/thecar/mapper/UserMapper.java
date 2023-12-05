package com.arsyux.thecar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arsyux.thecar.domain.UserVO;

@Mapper
public interface UserMapper {

	// ========================================
	// 1. 회원가입
	// ========================================
	
	// 회원가입
	@Insert("INSERT INTO tb_user(username, password, name, birth, gender, phone, email) "
		  + "VALUES(#{username}, #{password}, #{name}, #{birth}, #{gender}, #{phone}, #{email})")
	public void insertUser(UserVO user);
	
	// ========================================
	// 2. 회원 검색
	// ========================================
	
	// 회원 번호로 회원 1명 조회
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE userid = #{userid}")
	public UserVO findById(int userid);
	
	// 로그인 아이디로 회원 1명 조회
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE username = #{username}")
	public UserVO findByUsername(String username);
	
	// 휴대폰으로 회원 1명 조회
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE phone = #{phone}")
	public UserVO findByPhone(String phone);
	
	// 휴대폰으로 회원 1명 조회
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE email = #{email}")
	public UserVO findByEmail(String email);
	
	// 아이디 찾기
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE name = #{name} AND phone = #{phone} AND email = #{email}")
	public UserVO findUsername(UserVO user);
	
	// 비밀번호 찾기
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE username = #{username} AND name = #{name} AND phone = #{phone} AND email = #{email}")
	public UserVO findPassword(UserVO user);
	
	
	
	
	
	
	
	
	// 유저 리스트 가져오기
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "ORDER BY username DESC")
	public List<UserVO> getUserList();

	

	@Update("UPDATE tb_user "
		  + "SET PASSWORD = #{password} "
		  + "WHERE userid = #{userid}")
	public void updateUser(UserVO user);
	
	@Update("UPDATE tb_user "
		  + "SET PASSWORD = #{password} "
		  + "WHERE userid = #{userid}")
	public void changePassword(UserVO user);

	@Delete("DELETE tb_user "
		  + "WHERE userid = #{userid}")
	public void deleteUser(UserVO user);

}
