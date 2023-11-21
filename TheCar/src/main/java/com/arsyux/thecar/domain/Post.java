package com.arsyux.thecar.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	// 게시글 번호 (PK)
	private int postid;
	
	// 상태
	private String state;
	
	// 제목
	private String title;
	
	// 내용
	private String content;

	// 조회수
	private int cnt;
	
	// 작성 날짜
	private Timestamp regdate;

	// 글쓴이 회원 번호 (FK)
	private int userid;
	
	// 글쓴이 이름
	private String name;
	
	// 총 게시글 갯수
	private int last;
	
}
