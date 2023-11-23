package com.arsyux.thecar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.domain.SearchPage;
import com.arsyux.thecar.domain.User;

@Mapper
public interface PostMapper {
	
	// ========================================
	// 1. 게시글 개수 조회
	// ========================================
	
	// 전체 게시글 개수 조회
	@Select("SELECT count(*) FROM POST")
	public int getPostCount();
	
	// 유저이름 게시글 개수 조회
	@Select("SELECT count(*) FROM POST WHERE userid = #{userid}")
	public int getPostCountByUsername(User user);
		
	// 제목 게시글 개수 조회
	@Select("SELECT count(*) FROM POST WHERE TITLE LIKE '%#{title}%'")
	public int getPostCountByTitle(Post post);
	
	// 내용 게시글 개수 조회
	@Select("SELECT count(*) FROM POST WHERE TITLE LIKE '%#{content}%'")
	public int getPostCountByContent(Post post);
		
	// 제목내용 게시글 개수 조회
	@Select("SELECT count(*) FROM POST WHERE TITLE LIKE '%#{title}%' OR '%#{content}%'")
	public int getPostCountByTitleContent(Post post);
	
	// ========================================
	// 2. 게시글 조회
	// ========================================
	
	// 전체 게시글 전체 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name FROM USER u, POST p WHERE u.userid = p.userid ORDER BY p.postid DESC LIMIT #{start}, #{size}")
	public List<Post> getPostList(SearchPage searchPage);
	
	// 유저이름 게시글 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name FROM USER u, POST p WHERE u.userid = p.userid AND u.name = #{searchUsername} ORDER BY p.postid DESC LIMIT #{start}, #{size}")
	public List<Post> getPostListByUsername(SearchPage searchPage);
	
	// 제목 게시글 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name FROM USER u, POST p WHERE u.userid = p.userid AND p.title = #{searchTitle} ORDER BY p.postid DESC LIMIT #{start}, #{size}")
	public List<Post> getPostListByTitle(SearchPage searchPage);
	
	// 내용 게시글 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name FROM USER u, POST p WHERE u.userid = p.userid AND p.content = #{searchContent} ORDER BY p.postid DESC LIMIT #{start}, #{size}")
	public List<Post> getPostListByContent(SearchPage searchPage);
	
	// 제목내용 게시글 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name FROM USER u, POST p WHERE u.userid = p.userid AND p.title = #{searchTitle} OR p.content = #{searchContent} ORDER BY p.postid DESC LIMIT #{start}, #{size}")
	public List<Post> getPostListByTitleContent(SearchPage searchPage);
	
	// postid로 게시글 조회
	@Select("SELECT p.* FROM POST p WHERE POSTID = #{postid}")
	public Post getPostByPostId(int postid);
	
	// ========================================
	// 3. 게시글 작성
	// ========================================
	
	// 게시글 작성
	@Insert("INSERT INTO POST(TITLE, CONTENT, USERID) VALUES(#{title}, #{content}, #{userid})")
	public void insertPost(Post post);
	
}
