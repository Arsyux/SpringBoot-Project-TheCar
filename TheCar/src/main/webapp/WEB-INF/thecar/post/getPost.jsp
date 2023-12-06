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


	<c:if test="${empty post}">
		<h1>게시글 작성자와 관리자만 확인할 수 있습니다.</h1>
	</c:if>
	
	<c:if test="${!empty post }">
		<!-- 메인 컨텐츠 -->
		<div class="col-md-12 p-3">
			<div class="mainBorder p-3">
				<input type="hidden" id="postid" value="${ post.postid }">
				<div class="mb-1">
					<c:if test="${ post.state == 'R' }">
	      				<span style="background-color: red; border-color: red; border-style: solid; border-radius: 20%; color: white;">대기</span>
	      			</c:if>
	      			<c:if test="${ post.state == 'P' }">
	      				<span style="background-color: orange; border-color: orange; border-style: solid; border-radius: 20%; color: white;">처리</span>
	      			</c:if>
	      			<c:if test="${ post.state == 'C' }">
	      				<span style="background-color: green; border-color: green; border-style: solid; border-radius: 20%; color: white;">완료</span>
	      			</c:if>
	      			&nbsp;<b>${ post.title }</b>
				</div>
				<div class="mb-1">
					<!-- 날짜 데이터의 숫자가 소수점으로 표시되어서 Format변경 -->
		        	<fmt:parseDate value="${ post.regdate }" var="dateFormat" pattern="yyyy-MM-dd HH:mm:ss"/>
					<fmt:formatDate value="${ dateFormat }" var="regdate" pattern="yyyy-MM-dd HH:mm:ss"/>
	      			${ post.name }&nbsp;│&nbsp;${ regdate }
				</div>
				<hr>
				<div>
					${ post.content }
				</div>
				<button class="btn btn-danger" id="btn-delete">삭제</button>
				
				
				
				
			</div>
		</div>
	</c:if>
	
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
</div>

<script src="/js/post.js"></script>

<%@ include file="../layout/footer.jsp" %>