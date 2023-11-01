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
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
	
	<!-- login.css -->
	<link rel="stylesheet" type="text/css" href="/css/header.css">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark sticky-top" style="background-color: #111111;">
		<div class="container-md">

			<a class="navbar-brand" href="/"><img class="rounded-pill" src="/image/main/logo.png" style="width: 100%; max-width: 230px;"></a>

			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse justify-content-center row" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="col"></li>
					<li class="col"></li>
					<li class="col nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" style="color: #CCCCCC;">회사소개</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="/info/greetings">인사말</a></li>
							<li><a class="dropdown-item" href="/info/outline">회사개요</a></li>
							<li><a class="dropdown-item" href="/info/wayToCome">오시는길</a></li>
						</ul>
					</li>
					<li class="col nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" style="color: #CCCCCC;">카캐리어</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="/info/fiveTonCarCarrier">5톤 카캐리어</a></li>
							<li><a class="dropdown-item" href="/info/fullCarTrailerCarCarrier">풀카 트레일러 카캐리어</a></li>
						</ul>
					</li>
					<li class="col nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" style="color: #CCCCCC;">세이프티로더</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="/info/lowProfileSafety">저상형 SAFETY</a></li>
							<li><a class="dropdown-item" href="/info/solidSafety">고상형 SAFETY</a></li>
						</ul>
					</li>
					<li class="col nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" style="color: #CCCCCC;">로드탁송</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="/info/carConsignment">승용차탁송</a></li>
							<li><a class="dropdown-item" href="/info/largeCarConsignment">대형차탁송</a></li>
						</ul>
					</li>
					<li class="col nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" style="color: #CCCCCC;">제주도탁송</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="/info/jejuConsignment">제주도탁송</a></li>
						</ul>
					</li>
				</ul>
			</div>
			
		</div>
	</nav>
	<!-- 캐러셀 -->
	<div id="mainCarousel" class="carousel slide" data-bs-ride="carousel">
		<!-- 캐러셀 이미지 -->
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="/image/main/safetyLoader1.png" class="d-block w-100">
			</div>
			<div class="carousel-item">
				<img src="/image/main/safetyLoader2.png" class="d-block w-100">
			</div>
		</div>
	</div>