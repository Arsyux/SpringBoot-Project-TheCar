package com.arsyux.thecar.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	private int id;
	
	// 제목
	private String title;
	
	// 내용
	private String content;
	
	// 작성 날짜
	private Timestamp createDate;
	
	// 조회수
	private int cnt;
	
}
