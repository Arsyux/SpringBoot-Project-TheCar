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
<title>더카탁송 1 5 9 9 - 5 8 6 6</title>
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
	<!-- header -->
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">더카탁송 1599-5866</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="mynavbar">
				<c:if test="${ principal == null }">
					<ul class="navbar-nav me-auto">
						<li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/insertUser">회원가입</a></li>
					</ul>
				</c:if>
				<c:if test="${ principal != null }">
					<ul class="navbar-nav me-auto">
						<li class="nav-item"><a class="nav-link" href="/user/updateUser">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="/post/insertPost">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/logout">로그아웃</a></li>
					</ul>
				</c:if>
			</div>
		</div>
	</nav>
	<!-- header end -->