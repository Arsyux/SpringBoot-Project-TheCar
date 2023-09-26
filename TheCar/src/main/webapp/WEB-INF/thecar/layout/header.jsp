<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 정수의 몫을 구하기 위해 라이브러리 추가 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- JSP에서 다국어 메시지를 출력하기 위해 스프링에서 제공하는 태그 라이브러리를 선언한다. -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!-- 시큐리티가 제공하는 커스텀 라이브러리에 대한 태그 라이브러리 설정 -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!-- 로그인 인증에 성공한 브라우저만 접근할 수 있는 영역 -->
<sec:authorize access="isAuthenticated()">
	<!--
		principal은 로그인을 성공한 사용자(User) 객체에 접근할 수 있는 변수이다.
		스프링 시큐리티에서 제공하는 sec:authorize 태그를 사용하여 인증에 성공했을 때 principal이라는 변수에
		사용자 정보가 저장된 principal 객체가 할당되도록 한다.
		이를 통해 principal 변수에 할당된 사용자 정보를 화면에 출력할 수 있으며 principal 객체의 존재 여부를 기준으로 메뉴 구성을 다르게 나타낼 수도 있다.
	-->
	<sec:authentication var="principal" property="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
	<title>더카탁송 1599-5866</title>
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
	
	<!-- login.css -->
	<link rel="stylesheet" type="text/css" href="/css/header.css">
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
	<!--
		<li class="nav-item"><a class="nav-link" href="/post/insertPost">포스트 등록</a></li>
	-->
	<nav class="navbar navbar-expand-md navbar-dark sticky-top" style="background-color: #111111;">
		<div class="container-md">

			<a class="navbar-brand" href="/"><img class="rounded-pill" src="/image/main/logo.png" style="width: 100%; max-width: 230px;"></a>

			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/consignment/reservationConsignment" style="color: #CCCCCC;">탁송 예약</a></li>
					
					
					<li class="nav-item"><a class="nav-link" href="#" style="color: #CCCCCC;">탁송 예약 확인</a></li>
					
					<c:choose>
						<c:when test="${ principal == null }">
						
							<li class="nav-item"><a class="nav-link" href="/auth/login" style="color: #CCCCCC;"><i class="bi bi-box-arrow-in-right"></i> 로그인</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="/user/updateUser" style="color: #CCCCCC;"><i class="bi bi-person"></i> 회원 정보</a></li>
							<li class="nav-item"><a class="nav-link" href="/auth/logout" style="color: #CCCCCC;"><i class="bi bi-box-arrow-in-left"></i> 로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			
		</div>
	</nav>