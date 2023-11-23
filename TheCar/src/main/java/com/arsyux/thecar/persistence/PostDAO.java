package com.arsyux.thecar.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.domain.SearchPage;
import com.arsyux.thecar.domain.User;

@Repository
public class PostDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// ========================================
	// 1. 게시글 개수 조회
	// ========================================
	
	// 전체 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCount() { return mybatis.selectOne("getPostCount"); }
	
	// 유저이름 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByUsername(User user) { return mybatis.selectOne("getPostCountByUsername", user); }
	
	// 제목 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByTitle(Post post) { return mybatis.selectOne("getPostCountByTitle", post); }
	
	// 내용 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByContent(Post post) { return mybatis.selectOne("getPostCountByContent", post); }
	
	// 제목내용 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByTitleContent(Post post) { return mybatis.selectOne("getPostCountByTitleContent", post); }
	
	// ========================================
	// 2. 게시글 조회
	// ========================================
	
	// 전체 게시글 전체 조회
	public List<Post> getPostList(SearchPage searchPage) { return mybatis.selectList("getPostList", searchPage); }
	
	// 유저이름 게시글 조회
	public List<Post> getPostListByUsername(SearchPage searchPage) { return mybatis.selectList("getPostListByUsername", searchPage); }
	
	// 제목 게시글 조회
	public List<Post> getPostListByTitle(SearchPage searchPage) { return mybatis.selectList("getPostListByTitle", searchPage); }
	
	// 내용 게시글 조회
	public List<Post> getPostListByContent(SearchPage searchPage) { return mybatis.selectList("getPostListByContent", searchPage); }
	
	// 제목내용 게시글 조회
	public List<Post> getPostListByTitleContent(SearchPage searchPage) { return mybatis.selectList("getPostListByTitleContent", searchPage); }
	
	// postid로 게시글 조회
	public Post getPostByPostId(int postid) { return mybatis.selectOne("getPostByPostId", postid); }
	
	// ========================================
	// 3. 게시글 작성
	// ========================================
	
	// 게시글 작성
	public void insertPost(Post post) { mybatis.insert("insertPost", post); }
	
}
