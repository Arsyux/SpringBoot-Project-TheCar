<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!-- 로그인 인증에 성공한 브라우저만 접근할 수 있는 영역 -->
<sec:authorize access="isAuthenticated()">
	<!-- principal은 로그인 성공한 사용자(User) 객체에 접근할 수 있는 변수 -->
	<sec:authentication var="principal" property="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
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
<!-- header.css -->
<link rel="stylesheet" type="text/css" href="/css/header.css">

</head>
<body>
	<!-- header -->
	<!-- 
	<div class="row" style="background-color: #111111;">
		<div class="container">
			<div class="col-12-md">
				<c:if test="${ principal == null }">
					<a class="btn" href="/auth/login" style="color: #fafafa">로그인</a>
					<a class="btn" href="/auth/insertUser" style="color: #fafafa">회원가입</a>
				</c:if>
				<c:if test="${ principal != null }">
					<a class="btn" href="/user/updateUser" style="color: #fafafa">회원정보</a>
					<a class="btn" href="/post/insertPost" style="color: #fafafa">글쓰기</a>
					<a class="btn" href="/auth/logout" style="color: #fafafa">로그아웃</a>
				</c:if>
			</div>
		</div>
	</div>
	-->
	<nav class="navbar navbar-expand-md navbar-dark sticky-top" style="background-color: #111111;">
		<div class="container-md">

			<a class="navbar-brand me-5" href="/"><img class="rounded-pill" src="/image/main/logo.png" style="width: 100%; max-width: 230px;"></a>

			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav" style="width: 100%;">
					<li class="nav-item"><a class="nav-link" href="#">회사소개</a></li>
					<li class="nav-item"><a class="nav-link" href="#">차량탁송</a></li>
					<li class="nav-item"><a class="nav-link" href="#">캐리어탁송</a></li>
					<c:if test="${ principal == null }">
						<li class="nav-item"><a class="nav-link" href="#">탁송예약</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
					</c:if>
					<c:if test="${ principal != null }">
						<li class="nav-item"><a class="nav-link" href="#">탁송예약</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/logout">로그아웃</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- header end -->