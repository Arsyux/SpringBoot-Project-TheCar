package com.arsyux.thecar.domain;

import lombok.Data;

@Data
public class PageUtils {
	
	private int start; // 게시글 시작 번호
	private int size = 10; // 한번에 가져올 게시글 크기
	private String searchTitle; // 제목 검색
	private String searchContent; // 내용 검색
	private String searchName; // 작성자 검색
	private int postCount; // 총 게시글 수
	
	public PageUtils(int start, int postCount) {
		this.start = start;
		this.postCount = postCount;
	}
	
	public PageUtils(int start, int postCount, String searchName) {
		this.start = start;
		this.postCount = postCount;
		this.searchName = searchName;
	}
	
	public PageUtils(int start, int postCount, String searchTitle, String searchContent) {
		this.start = start;
		this.postCount = postCount;
		this.searchTitle = searchTitle;
		this.searchContent = searchContent;
	}
	
}
