package com.arsyux.thecar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 응답 메시지로 사용할 DTO 클래스
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

	// ResponseDTO 클래스에 HTTP 응답 상태를 저장할 status 변수와 응답 결과 데이터를 저장할 data 변수를 선언한다.
	
	// Http 응답 상태 코드
	private int status;
	
	// 실제 응답할 데이터
	private T data;
	
}
