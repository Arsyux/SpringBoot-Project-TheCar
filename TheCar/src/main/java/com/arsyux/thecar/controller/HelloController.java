package com.arsyux.thecar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	// 직접 호출
	// 스프링 부트는 정적 웹 콘텐츠에 해당하는 HTML이나 이미지 파일 등을 src/main/resource/static 폴더에서 관리한다.
	// 브라우저에 http://localhost:8080/hello.html를 입력하여 직접 호출이 가능하다.
	
	// 간접 호출
	// static 폴더에 생성한 정적 콘텐츠는 컨트롤러를 이용하여 간접적으로 호출할 수도 있다.
	// HelloController 클래스에 @Controller를 설정하여 해당 객체가 컴포넌트로 스캔되도록 한다.
	// 그리고 html() 메소드와 image()메소드에는 @GetMapping을 설정하여 브라우저로부터의 GET방식 요청을 처리하도록 한다.
	// http://localhost:8080/hello를 입력하여 간접 호출을 확인한다.
	@GetMapping("/html")
	public String html() {
		System.out.println("HTML 파일이 요청됨");
		return "redirect:hello.html";
	}
	
	@GetMapping("/image")
	public String image() {
		System.out.println("이미지 파일이 요청됨");
		return "redirect:image/arsyux.png";
	}
}
