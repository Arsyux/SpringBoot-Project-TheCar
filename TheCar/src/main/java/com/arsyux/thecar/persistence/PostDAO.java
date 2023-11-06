package com.arsyux.thecar.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.domain.SearchPage;

@Repository
public class PostDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 회원 가입
	public void insertPost(Post post) {
		mybatis.insert("insertPost", post);
	}
	
	// 게시글 총 갯수 조회
	@Transactional(readOnly = true)
	public int getPostMaxCount() {
		return mybatis.selectOne("getPostMaxCount");
	}
	
	// 메인화면 조회
	public List<Post> getPostList() {
		return mybatis.selectList("getPostList");
	}
	
	// 메인화면 조회
	public List<Post> getTestList(SearchPage searchPage) {
		return mybatis.selectList("getTestList", searchPage);
	}
	
}
