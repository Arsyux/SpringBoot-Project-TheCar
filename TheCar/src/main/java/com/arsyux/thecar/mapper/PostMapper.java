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
	@Select("SELECT count(*) "
		  + "FROM tb_post")
	public int getPostCount();
	
	// 유저이름 게시글 개수 조회
	@Select("SELECT count(*) "
		  + "FROM tb_post "
		  + "WHERE userid = #{userid}")
	public int getPostCountByUserid(User user);
		
	// 제목 게시글 개수 조회
	@Select("SELECT count(*) "
		  + "FROM tb_post "
		  + "WHERE title LIKE '%#{title}%'")
	public int getPostCountByTitle(Post post);
	
	// 내용 게시글 개수 조회
	@Select("SELECT count(*) "
		  + "FROM tb_post "
		  + "WHERE title LIKE '%#{content}%'")
	public int getPostCountByContent(Post post);
		
	// 제목내용 게시글 개수 조회
	@Select("SELECT count(*) "
		  + "FROM tb_post "
		  + "WHERE title LIKE '%#{title}%' OR content LIKE '%#{content}%'")
	public int getPostCountByTitleContent(Post post);
	
	// ========================================
	// 2. 게시글 조회
	// ========================================
	
	// 전체 게시글 전체 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name "
		  + "FROM tb_user u, tb_post p "
		  + "WHERE u.userid = p.userid "
		  + "ORDER BY p.postid DESC "
		  + "LIMIT #{start}, #{size}")
	public List<Post> getPostList(SearchPage searchPage);
	
	// 유저이름 게시글 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name "
		  + "FROM tb_user u, tb_post p "
		  + "WHERE u.userid = p.userid AND u.username = #{searchUsername} "
		  + "ORDER BY p.postid DESC "
		  + "LIMIT #{start}, #{size}")
	public List<Post> getPostListByUsername(SearchPage searchPage);
	
	// 제목 게시글 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name "
		  + "FROM tb_user u, tb_post p "
		  + "WHERE u.userid = p.userid AND p.title = #{searchTitle} "
		  + "ORDER BY p.postid DESC "
		  + "LIMIT #{start}, #{size}")
	public List<Post> getPostListByTitle(SearchPage searchPage);
	
	// 내용 게시글 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name "
		  + "FROM tb_user u, tb_post p "
		  + "WHERE u.userid = p.userid AND p.content = #{searchContent} "
		  + "ORDER BY p.postid DESC "
		  + "LIMIT #{start}, #{size}")
	public List<Post> getPostListByContent(SearchPage searchPage);
	
	// 제목내용 게시글 조회
	@Select("SELECT p.postid, p.state, p.title, p.regdate, u.name name "
		  + "FROM tb_user u, tb_post p "
		  + "WHERE u.userid = p.userid AND p.title = #{searchTitle} OR p.content = #{searchContent} "
		  + "ORDER BY p.postid DESC "
		  + "LIMIT #{start}, #{size}")
	public List<Post> getPostListByTitleContent(SearchPage searchPage);
	
	// postid로 게시글 조회
	@Select("SELECT p.postid, p.state, p.title, p.content, p.regdate, u.name name, p.userid "
		  + "FROM tb_user u, tb_post p "
		  + "WHERE p.userid = u.userid AND p.postid = #{postid}")
	public Post getPostByPostId(int postid);
	
	// ========================================
	// 3. 게시글 작성
	// ========================================
	
	// 게시글 작성
	@Insert("INSERT INTO tb_post(title, content, departures_postcode, departures_address, departures_detailAddress, departures_extraAddress, "
		  + "arrivals_postcode, arrivals_address, arrivals_detailAddress, arrivals_extraAddress, userid) "
		  + "VALUES(#{title}, #{content}, #{departures_postcode}, #{departures_address}, #{departures_detailAddress}, #{departures_extraAddress}, "
		  + "#{arrivals_postcode}, #{arrivals_address}, #{arrivals_detailAddress}, #{arrivals_extraAddress}, #{userid})")
	public void insertPost(Post post);
	
}
