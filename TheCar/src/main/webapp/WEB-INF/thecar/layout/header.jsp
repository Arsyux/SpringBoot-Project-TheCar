<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 -->
<link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<script src="/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
<!-- 제이쿼리 -->
<script src="/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
<!-- 서머노트 -->
<link href="/webjars/summernote/0.8.10/summernote-bs4.css" rel="stylesheet">
<script src="/webjars/summernote/0.8.10/summernote-bs4.min.js"></script>
<!-- 아이콘 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
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
	
		인덱스 페이지
		인덱스 페이지는 웹 애플리케이션을 이용하는 사용자가 처음으로 접하는 화면이다.
		일반적으로 자바 기반의 웹 애플리케이션은 index.jsp파일을 이용하여 인덱스 페이지를 구성한다.
 	-->

	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">Main</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="mynavbar">
				<c:choose>
					<c:when test="${sessionScope.principal == null}">
						<ul class="navbar-nav me-auto">
							<li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
							<li class="nav-item"><a class="nav-link" href="/auth/insertUser">회원가입</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="navbar-nav me-auto">
							<li class="nav-item"><a class="nav-link" href="/user/updateUser">회원 정보</a></li>
							<li class="nav-item"><a class="nav-link" href="/post/insertPost">포스트 등록</a></li>
							<li class="nav-item"><a class="nav-link" href="/auth/logout">로그아웃</a></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>