package com.arsyux.thecar.exception;

// 예외 처리 핸들러
// RuntimeException이 아니라 Exception을 상속받아도 됨
public class TheCarException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TheCarException(String message) {
		super(message);
	}
}
