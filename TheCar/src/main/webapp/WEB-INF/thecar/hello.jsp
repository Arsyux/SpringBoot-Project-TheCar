<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--
	동적 콘텐츠 관리
	스프링 부트에서 동적 콘텐츠의 위치는 src/main/resources/templates 폴더지만
	JSP는 스프링 부트에서 지원하는 템플릿이 아니므로 JSP를 위한 별도의 폴더 구조를 만들어야 한다.
	application.yml 파일을 통해 ViewResolver 객체의 속성을 변경했고,
	이때 프로젝트에서 사용할 JSP 파일들의 위치를 /WEB-INF/thecar/로 지정했다.
	따라서 다음과 같이 src/main 폴더에 webapp폴더를 생성하고, 하위에 WEB-INF폴더와 thecar 폴더를 순차적으로 생성한 후,
	thecar폴더에 hello.jsp 파일을 작성한다.
-->
<h1>Hello, ${ username }!</h1>
</body>
</html>