package com.arsyux.thecar.domain;

import lombok.Data;

@Data
public class SearchPage {
	
	public SearchPage() { }
	
	public SearchPage(int start, int size) {
		this.start = start;
		this.size = size;
	}
	
	public SearchPage(int start, int size, String searchText) {
		this.start = start;
		this.size = size;
		this.searchText = searchText;
	}
	
	// 게시글 시작 번호
	private int start;
	
	// 게시글 종료 번호
	private int size;

	// 검색 단어
	private String searchText;
	
}
