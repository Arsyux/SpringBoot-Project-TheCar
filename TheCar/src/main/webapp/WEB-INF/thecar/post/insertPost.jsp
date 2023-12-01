<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- main.css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">

<div class="container mt-3" style="min-height: 500px;">
	<div class="row">
		<!-- 글 작성하기 -->
		<div class="col-sm-12 p-3">
			<div class="mainBorder p-3">
				<h2>문의하기</h2>
				<hr>
				<form>
					<div class="mb-3">
						<input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요.">
					</div>
					<div class="mb-3">
						<label for="content">내용</label>
						<textarea class="form-control" rows="5" id="content"></textarea>
					</div>
				</form>
				
				<button id="btn-insert" class="btn btn-secondary">포스트 등록</button>
					
					
					
					
			</div>
		</div>
	
	
	</div>
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