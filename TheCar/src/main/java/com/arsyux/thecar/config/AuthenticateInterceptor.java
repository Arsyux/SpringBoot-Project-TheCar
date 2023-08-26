package com.arsyux.thecar.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.arsyux.thecar.domain.User;

// 인터셉터 적용
// 현재 웹 애플리케이션에서는 로그인 인증 여부와 무관하게 인덱스 페이지에서 포스트 목록을 보거나 포스트를 등록할 수 있다.
// 모든 컨트롤러의 메소드에 인증 확인과 관련된 코드를 추가하는 것은 매우 번거롭다.
// -> 스프링의 인터셉터를 적용.
// 인터셉터는 디스패처 서블릿(DispatcherServlet)과 컨트롤러 사이에 위치하며,
// 이를 통해 컨트롤러 시행 전/후의 사전 처리와 사후 처리 로직을 추가할 수 있다.
// 즉, 서블릿에서의 필터(Filter)처럼 컨트롤러의 요청을 가로채는 개념이다.
public class AuthenticateInterceptor implements HandlerInterceptor {

	// HandlerInterceptor 인터페이스는 default 메소드로만 구성되어 있으므로
	// 모든 메소드에 대한 오버라이딩을 필수로 할 필요가 없다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { 
		// 세션에 회원 정보가 존재하는지 확인
		HttpSession session = request.getSession();		
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			// 세션에 principal이라는 이름의 사용자 정보가 없으면 다시 로그인 화면으로 이동한다.
			response.sendRedirect("/auth/login");
		}
		return true;
	}
	
	
	
}
