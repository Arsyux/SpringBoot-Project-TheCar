package com.arsyux.thecar.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {

	private int fileid; // 파일 번호(PK)
	private int postid; // 게시글 번호 (FK)
	private String original_name; // 원본 파일명
	private String save_name; // 저장 파일명
	
	private Timestamp regdate; // 날짜
	
}
