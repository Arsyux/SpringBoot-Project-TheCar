package com.arsyux.thecar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// UserController클래스에서 응답 메시지로 사용할 데이터 전송 객체(Data Transfer Object, DTO)를 생성한다.
// 컨트롤러의 응답으로 DTO를 사용하는 가장 큰 이유는 클라이언트에게 일관성 있는 응답을 전송하기 위해서이다.
// 예를 들어, 컨트롤러의 메소드가 브라우저의 요청에 대해 성공과 실패의 의미로 1과 0을 반환한다고 가정하면,
// 당연히 서버의 응답 결과를 받는 쪽에서 반환 값의 의미를 알고 있어야 한다.
// 따라서 응답의 의미나 종류가 임의로 변경되면 그에 맞게 프로그램도 수정되어야 한다.
// 이러한 서버의 응답을 일관성 있게 처리하기 위해서는 표준화된 응답 데이터 형식이 필요하다.
// 따라서 HTTP 응답상태(status) 정보를 DTO로 감싸서 처리한다.
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
