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


<div class="container mt-3" style="min-height: 500px;">
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
						<a href="/auth/loginUser" class="btn btn-dark w-100">로그인</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="mainBorder p-3">
						<div class="row">
							<div class="col-12 mb-2">
								<a href="/user/updateUser" style="font-weight: bold; text-decoration: none; color: black;">${ principal.user.name }</a><span>님, 환영합니다!</span>
							</div>
							<div class="col-5 mb-2">
								<span>내가 쓴 글 </span>
							</div>
							<div class="col-7 mb-2">
								<a href="#" style="font-weight: bold; text-decoration: none; color: black;"><span style="font-weight: bold;">100,000,000</span></a><span> 개</span>
							</div>
							<div class="col-5 mb-2">
								<span>나의 포인트 </span>
							</div>
							<div class="col-7 mb-2">
								<span style="font-weight: bold;">${ principal.user.point }</span><span> 점</span>
							</div>
							<div class="col-12">
								<a href="/auth/logout" class="btn btn-dark w-100">로그아웃</a>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>


<%@ include file="./layout/footer.jsp" %>