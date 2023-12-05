package com.arsyux.thecar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.PostVO;
import com.arsyux.thecar.domain.PageUtils;
import com.arsyux.thecar.domain.UserVO;
import com.arsyux.thecar.persistence.PostDAO;

@Service
public class PostService {

	@Autowired
	private PostDAO postDAO;
	
	// ========================================
	// 1. 게시글 개수 조회
	// ========================================
	
	// 전체 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCount() { return postDAO.getPostCount(); }
	
	// 유저이름 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByUserid(UserVO user) { return postDAO.getPostCountByUserid(user); }
	
	// 제목 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByTitle(PostVO post) { return postDAO.getPostCountByTitle(post); }
	
	// 내용 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByContent(PostVO post) { return postDAO.getPostCountByContent(post); }
	
	// 제목내용 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByTitleContent(PostVO post) { return postDAO.getPostCountByTitleContent(post); }
	
	// ========================================
	// 2. 게시글 조회
	// ========================================
	
	// 전체 게시글 전체 조회
	@Transactional(readOnly = true)
	public List<PostVO> getPostList(PageUtils searchPage) { return postDAO.getPostList(searchPage); }
	
	// 유저이름 게시글 조회
	@Transactional(readOnly = true)
	public List<PostVO> getPostListByUsername(PageUtils searchPage) { return postDAO.getPostListByUsername(searchPage); }
	
	// 제목 게시글 조회
	@Transactional(readOnly = true)
	public List<PostVO> getPostListByTitle(PageUtils searchPage) { return postDAO.getPostListByTitle(searchPage); }
	
	// 내용 게시글 조회
	@Transactional(readOnly = true)
	public List<PostVO> getPostListByContent(PageUtils searchPage) { return postDAO.getPostListByContent(searchPage); }
	
	// 제목내용 게시글 조회
	@Transactional(readOnly = true)
	public List<PostVO> getPostListByTitleContent(PageUtils searchPage) { return postDAO.getPostListByTitleContent(searchPage); }
	
	// postid로 게시글 조회
	@Transactional(readOnly = true)
	public PostVO getPostByPostId(int postid) { return postDAO.getPostByPostId(postid); }
	
	// 작성자의 가장 최신 게시글 조회
	@Transactional(readOnly = true)
	public PostVO getLastPostByUserid(UserVO user) { return postDAO.getLastPostByUserid(user); }

	// ========================================
	// 3. 게시글 작성, 수정, 삭제
	// ========================================
	
	// 게시글 작성
	@Transactional
	public void insertPost(PostVO post) { postDAO.insertPost(post); }

	

	// ========================================
	// 4. 기타
	// ========================================
	
	
}
