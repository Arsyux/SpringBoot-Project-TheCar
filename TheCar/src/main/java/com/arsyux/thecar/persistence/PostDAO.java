package com.arsyux.thecar.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.Post;

@Repository
public class PostDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 회원 가입
	public void insertPost(Post post) {
		mybatis.insert("insertPost", post);
	}
	
	// 메인화면 조회
	public List<Post> getMainList() {
		return mybatis.selectList("getMainList");
	}

}
