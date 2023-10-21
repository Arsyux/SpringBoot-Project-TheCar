<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp"%>

<!-- main.css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">

<!-- 캐러셀 -->
<div id="mainCarousel" class="carousel slide" data-bs-ride="carousel">
	<!-- 캐러셀 이미지 -->
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="/image/main/Safety Loader1.png" class="d-block w-100">
		</div>
		<div class="carousel-item">
			<img src="/image/main/Safety Loader2.png" class="d-block w-100">
		</div>
	</div>
</div>


<div class="container mt-3">
	<div class="row">
		<!-- 메인 컨텐츠 -->
		<div class="col-md-9 col-sm-12 p-3">
			<div class="mainBorder p-3">
				<h1>Main</h1>
			</div>
		</div>
		
		<!-- 회원정보 -->
		<div class="col-md-3 col-sm-4 p-3">
			<c:choose>
				<c:when test="${ principal == null }">
					<div class="mainBorder p-3">
						<a href="/auth/loginUser" class="btn btn-secondary w-100">로그인</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="mainBorder p-3">
						<a href="/auth/loginUser" class="btn btn-secondary w-100">로그아웃</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>


<%@ include file="./layout/footer.jsp" %>