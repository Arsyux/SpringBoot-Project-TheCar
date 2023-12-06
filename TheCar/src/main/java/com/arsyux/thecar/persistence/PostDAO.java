package com.arsyux.thecar.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.PostVO;
import com.arsyux.thecar.domain.PageUtils;
import com.arsyux.thecar.domain.UserVO;

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
	
	// 유저번호로 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByUserid(UserVO user) { return mybatis.selectOne("getPostCountByUserid", user); }
	
	// 제목 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByTitle(PostVO post) { return mybatis.selectOne("getPostCountByTitle", post); }
	
	// 내용 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByContent(PostVO post) { return mybatis.selectOne("getPostCountByContent", post); }
	
	// 제목내용 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByTitleContent(PostVO post) { return mybatis.selectOne("getPostCountByTitleContent", post); }
	
	// ========================================
	// 2. 게시글 조회
	// ========================================
	
	// 전체 게시글 전체 조회
	public List<PostVO> getPostList(PageUtils searchPage) { return mybatis.selectList("getPostList", searchPage); }
	
	// 유저이름 게시글 조회
	public List<PostVO> getPostListByUsername(PageUtils searchPage) { return mybatis.selectList("getPostListByUsername", searchPage); }
	
	// 제목 게시글 조회
	public List<PostVO> getPostListByTitle(PageUtils searchPage) { return mybatis.selectList("getPostListByTitle", searchPage); }
	
	// 내용 게시글 조회
	public List<PostVO> getPostListByContent(PageUtils searchPage) { return mybatis.selectList("getPostListByContent", searchPage); }
	
	// 제목내용 게시글 조회
	public List<PostVO> getPostListByTitleContent(PageUtils searchPage) { return mybatis.selectList("getPostListByTitleContent", searchPage); }
	
	// postid로 게시글 조회
	public PostVO getPostByPostId(int postid) { return mybatis.selectOne("getPostByPostId", postid); }
	
	// 작성자의 가장 최신 게시글 조회
	public PostVO getLastPostByUserid(UserVO user) { return mybatis.selectOne("getLastPostByUserid", user); }
	
	// ========================================
	// 3. 게시글 작성, 삭제
	// ========================================
	
	// 게시글 작성
	public void insertPost(PostVO post) { mybatis.insert("insertPost", post); }
	
	// 게시글 삭제
	public void deletePost(PostVO post) { mybatis.delete("deletePost", post); }
	
}
