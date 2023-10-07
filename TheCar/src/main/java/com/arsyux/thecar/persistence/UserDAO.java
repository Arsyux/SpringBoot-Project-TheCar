package com.arsyux.thecar.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.User;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 회원 가입
	public void insertUser(User user) {
		mybatis.insert("insertUser", user);
	}
	
	// 회원 1명 조회
	public User getUser(User user) {
		return mybatis.selectOne("getUser", user);
	}
	
	public List<User> getUserList(){
		return mybatis.selectList("getUserList");
	}
	
	
	
	public void updateUser(User user) {
		mybatis.update("updateUser", user);
	}
	
	public void deleteUser(User user) {
		mybatis.delete("deleteUser", user);
	}
	
}
