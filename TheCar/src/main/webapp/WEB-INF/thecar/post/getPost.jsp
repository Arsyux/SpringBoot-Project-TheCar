<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br><br>
<div class="container border">
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
		작성자: 
	</div>
	
	<hr>
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	
	
</div>

<script src="/js/post.js"></script>
<script src="/js/reply.js"></script>

<%@ include file="../layout/footer.jsp" %>