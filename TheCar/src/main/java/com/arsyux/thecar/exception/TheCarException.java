package com.arsyux.thecar.exception;

// 시스템에서 문제가 발생한 경우, 사용자가 이해할 수 있는 메시지를 포함한 화면이 제공되어야 한다.
// 이를 위해, 스프링에서는 예외 처리 핸들러를 제공한다.
// 모든 예외의 최상위 부모 클래스인 Exception을 상속하여 사용자 정의 예외 클래스를 작성해도 되나
// RuntimeException 클래스를 상속하여 Unchecked 예외 클래스로 작성했다.
public class TheCarException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TheCarException(String message) {
		super(message);
	}
}
