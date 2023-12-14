<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!-- main.css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">

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

<div class="container">

	<!-- 메인 컨텐츠 -->
	<div class="row">
		<div class="col-md-12 p-3">
			<div class="mainBorder p-3" align="center">
				<h1>에러가 발생하였습니다.</h1>
				<h2>관리자에게 문의해주세요.</h2>
				<!--
				<div class="errorPage">
					<span class="errorHead">Error!</span><br/>
					<p>request_uri : <c:out value="${requestScope['javax.servlet.error.request_uri']}"/></p>
					<p>status_code : <c:out value="${requestScope['javax.servlet.error.status_code']}"/></p>
					<p>servlet_name : <c:out value="${requestScope['javax.servlet.error.servlet_name']}"/></p>
					<p>exception : <c:out value="${requestScope['javax.servlet.error.exception']}"/></p>
					<p>servlet_name : <c:out value="${requestScope['javax.servlet.error.servlet_name']}"/></p>
					<p>message : <c:out value="${requestScope['javax.servlet.error.message']}"/></p>
				</div>
				-->
			</div>
		</div>
	</div>
	
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
</div>

<%@ include file="../layout/footer.jsp" %>