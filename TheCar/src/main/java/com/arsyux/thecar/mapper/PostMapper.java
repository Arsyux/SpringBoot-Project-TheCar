package com.arsyux.thecar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.arsyux.thecar.domain.Post;

@Mapper
public interface PostMapper {

	// 회원 가입
	// pk는 AUTO_INCREMENT처리
	// cnt는 DEFAULT 0처리
	// regdate는 CURRENT_TIMESTAMP 처리
	@Insert("INSERT INTO POST(TITLE, CONTENT, UID) VALUES(#{title}, #{content}, #{uid})")
	public void insertPost(Post post);
	
	// 메인 화면 게시글 조회
	// -> 일반 게시글 조회로 써도 될지 확인 후 getPostList()로 이름 변경
	@Select("SELECT p.id, p.state, p.title, p.regdate, u.name name FROM USER u, POST p WHERE u.id = p.uid ORDER BY p.id DESC LIMIT 10")
	public List<Post> getMainList();
	
	// 게시글 전체 조회
	//@Select("SELECT * FROM USER ORDER BY USERNAME DESC")
	//public List<User> getUserList();
	
	
}
