package com.arsyux.thecar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.domain.SearchPage;

@Mapper
public interface PostMapper {

	// 게시글 작성
	// pk는 AUTO_INCREMENT처리
	// cnt는 DEFAULT 0처리
	// regdate는 CURRENT_TIMESTAMP 처리
	@Insert("INSERT INTO POST(TITLE, CONTENT, UID) VALUES(#{title}, #{content}, #{uid})")
	public void insertPost(Post post);
	
	// 게시글 총 갯수 조회
	@Select("SELECT count(*) FROM POST")
	public List<Post> getPostListMaxCount();
	
	// 제목 검색 게시글 총 갯수 조회
	@Select("SELECT count(*) FROM POST WHERE TITLE LIKE '%#{title}%'")
	public List<Post> getSearchTitlePostListMaxCount(Post post);
	
	// 제목댓글 검색 게시글 총 갯수 조회
	@Select("SELECT count(*) FROM POST WHERE TITLE LIKE '%#{title}%' OR '%#{content}%'")
	public List<Post> getSearchAllPostListMaxCount(Post post);
	
	// 게시글 전체 조회
	@Select("SELECT p.id, p.state, p.title, p.regdate, u.name name FROM USER u, POST p WHERE u.id = p.uid ORDER BY p.id DESC LIMIT 10")
	public List<Post> getPostList();
	
	// 테스트
	@Select("SELECT p.id, p.state, p.title, p.regdate, (select id from post order by id desc limit 1) last, u.name name FROM USER u, POST p WHERE u.id = p.uid ORDER BY p.id DESC LIMIT ${start}, #{size}")
	public List<Post> getTestList(SearchPage page);
	
	
	
}
