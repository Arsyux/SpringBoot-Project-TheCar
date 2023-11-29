<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br><br>
<div class="container border">


	<c:if test="${empty post}">
		<h1>게시글 작성자와 관리자만 확인할 수 있습니다.</h1>
	</c:if>
	
	<c:if test="${!empty post }">
	<br>
	<div>
		<h3>${ post.title }</h3>
	</div>
	<br>
	<div>
		<div>${ post.content }</div>
	</div>
	
	<br>
	<div>
		포스트 번호: <span id="postid"><i>${ post.postid }</i></span><br>
		작성자: ${ post.name }
	</div>
	
	<hr>
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	</c:if>
	
</div>

<script src="/js/post.js"></script>
<script src="/js/reply.js"></script>

<%@ include file="../layout/footer.jsp" %>