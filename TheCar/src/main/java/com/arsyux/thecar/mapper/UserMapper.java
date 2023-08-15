package com.arsyux.thecar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arsyux.thecar.domain.User;

// mybatis사용시 @Mapper가 설정된 interface에 추상 메소드를 선언하고
// @Select, @Insert, @Update, @Delete를 이용하여 SQL 구문을 등록한다.
// @Mapper가 설정된 인터페이스를 작성만 하면, 마이바티스에서 자동으로 매퍼 인터페이스가 구현된 클래스를 제공한다.
@Mapper
public interface UserMapper {

	@Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
	public User getUser(User user);

	@Select("SELECT * FROM USERS ORDER BY USERNAME DESC")
	public List<User> getUserList();

	@Insert("INSERT INTO USERS(ID, USERNAME, PASSWORD, EMAIL) "
			+ "VALUES((SELECT NVL(MAX(ID), 0)+1 FROM USERS), #{username}, #{password}, #{email})")
	public void insertUser(User user);

	@Update("UPDATE USERS SET PASSWORD = #{password}, EMAIL = #{email} WHERE ID = #{id}")
	public void updateUser(User user);

	@Delete("DELETE USERS WHERE ID = #{id}")
	public void deleteUser(User user);

}
