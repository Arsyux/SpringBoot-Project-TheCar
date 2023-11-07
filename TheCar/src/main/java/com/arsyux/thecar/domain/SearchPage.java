package com.arsyux.thecar.domain;

import lombok.Data;

@Data
public class SearchPage {
		
	public SearchPage(int start, int size, int postCount) {
		this.start = start;
		this.size = size;
		this.postCount = postCount;
	}
	
	public SearchPage(int start, int size, int postCount, String searchUsername) {
		this.start = start;
		this.size = size;
		this.postCount = postCount;
		this.searchUsername = searchUsername;
	}
	
	public SearchPage(int start, int size, int postCount, String searchTitle, String searchContent) {
		this.start = start;
		this.size = size;
		this.postCount = postCount;
		this.searchTitle = searchTitle;
		this.searchContent = searchContent;
	}
	
	// 게시글 시작 번호
	private int start;
	
	// 게시글 종료 번호
	private int size;

	// 제목 검색
	private String searchTitle;
	
	// 내용 검색
	private String searchContent;
	
	// 작성자 검색
	private String searchUsername;
	
	// 총 게시글 수
	private int postCount;
	
}
