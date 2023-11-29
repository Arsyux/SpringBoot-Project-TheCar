package com.arsyux.thecar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.domain.SearchPage;
import com.arsyux.thecar.domain.User;
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
	public int getPostCountByUserid(User user) { return postDAO.getPostCountByUserid(user); }
	
	// 제목 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByTitle(Post post) { return postDAO.getPostCountByTitle(post); }
	
	// 내용 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByContent(Post post) { return postDAO.getPostCountByContent(post); }
	
	// 제목내용 게시글 개수 조회
	@Transactional(readOnly = true)
	public int getPostCountByTitleContent(Post post) { return postDAO.getPostCountByTitleContent(post); }
	
	// ========================================
	// 2. 게시글 조회
	// ========================================
	
	// 전체 게시글 전체 조회
	@Transactional(readOnly = true)
	public List<Post> getPostList(SearchPage searchPage) { return postDAO.getPostList(searchPage); }
	
	// 유저이름 게시글 조회
	@Transactional(readOnly = true)
	public List<Post> getPostListByUsername(SearchPage searchPage) { return postDAO.getPostListByUsername(searchPage); }
	
	// 제목 게시글 조회
	@Transactional(readOnly = true)
	public List<Post> getPostListByTitle(SearchPage searchPage) { return postDAO.getPostListByTitle(searchPage); }
	
	// 내용 게시글 조회
	@Transactional(readOnly = true)
	public List<Post> getPostListByContent(SearchPage searchPage) { return postDAO.getPostListByContent(searchPage); }
	
	// 제목내용 게시글 조회
	@Transactional(readOnly = true)
	public List<Post> getPostListByTitleContent(SearchPage searchPage) { return postDAO.getPostListByTitleContent(searchPage); }
	
	// postid로 게시글 조회
	@Transactional(readOnly = true)
	public Post getPostByPostId(int postid) { return postDAO.getPostByPostId(postid); }
	
	// ========================================
	// 3. 게시글 작성, 수정, 삭제
	// ========================================
	
	// 게시글 작성
	@Transactional
	public void insertPost(Post post) { postDAO.insertPost(post); }

	

	// ========================================
	// 4. 기타
	// ========================================
	
	
}
