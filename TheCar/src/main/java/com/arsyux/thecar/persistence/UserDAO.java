package com.arsyux.thecar.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	// ========================================
	// 회원가입
	// ========================================
	
	// 회원 가입
	public void insertUser(UserVO user) {
		mybatis.insert("insertUser", user);
	}
	
	// ========================================
	// 회원검색
	// ========================================
	
	// 회원 번호로 1명 조회
	public UserVO findById(int id) {
		return mybatis.selectOne("findById", id);
	}
	// 로그인 아이디로 회원 1명 조회
	public UserVO findByUsername(String username) {
		return mybatis.selectOne("findByUsername", username);
	}
	// 휴대폰으로 회원 1명 조회
	public UserVO findByPhone(String phone) {
		return mybatis.selectOne("findByPhone", phone);
	}
	// 휴대폰으로 회원 1명 조회
	public UserVO findByEmail(String email) {
		return mybatis.selectOne("findByEmail", email);
	}
	// 아이디 찾기
	public UserVO findUsername(UserVO user) {
		return mybatis.selectOne("findUsername", user);
	}
	// 비밀번호 찾기
	public UserVO findPassword(UserVO user) {
		return mybatis.selectOne("findPassword", user);
	}
	// 게시글 번호로 유저 찾기
	public UserVO findByPostId(int postid) {
		return mybatis.selectOne("findByPostId", postid);
	}
	
	// 회원 정보 변경
	public void updateUser(UserVO user) {
		mybatis.update("updateUser", user);
	}
	
	// 비밀번호 변경
	public void changePassword(UserVO user) {
		mybatis.update("changePassword", user);
	}
	
	// 회원 탈퇴
	public void deleteUser(UserVO user) {
		mybatis.delete("deleteUser", user);
	}
	
	// 회원 리스트 가져오기
	public List<UserVO> getUserList() {
		return mybatis.selectList("getUserList");
	}
	
	// 포인트 적립
	public void updatePoint(UserVO user) {
		mybatis.update("updatePoint", user);
	}
	
	
}
