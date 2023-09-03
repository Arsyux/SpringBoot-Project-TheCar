<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br>
<div class="container mt-3">
	<form>
		<!-- 포스트 수정 기능에 사용할 post의 id정보를 hidden으로 전달한다. -->
		<input type="hidden" id="id" value="${ post.id }">
		<div class="mb-3">
			<label for="title">Title:</label>
			<input type="text" class="form-control" id="title" value="${ post.title }">
		</div>
		<div class="mb-3">
			<label for="content">Content:</label>
			<textarea class="form-control" rows="5" id="content">${ post.content }</textarea>
		</div>
	</form>
	
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<button id="btn-update" class="btn btn-secondary">포스트 수정</button>
	
</div>

<script>
<!-- 서머노트 적용 -->
$(document).ready(function () {
	// <textarea> 태그의 id가 content
	$("#content").summernote({
		height: 300
	});
});
</script>

<script src="/js/post.js"></script>

<%@ include file="../layout/footer.jsp" %>