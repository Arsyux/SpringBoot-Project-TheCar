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
		포스트 번호: <span id="id"><i>${ post.id }</i></span><br>
		작성자: <span><i>${ post.user.username }</i></span>
	</div>
	
	<hr>
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	
	<!-- 자신이 등록한 포스트만 수정하거나 삭제할 수 있도록 세션에 등록된 사용자의 아이디와 POST를 등록한 사용자의 아이디가 같은지 비교한다. -->
	<c:if test="${ post.user.username == principal.username }">
		<a href="/post/updatePost/${ post.id }" class="btn btn-warning">수정하기</a>
		<button id="btn-delete" class="btn btn-danger">삭제하기</button>
	</c:if>
	
	<br><br>
	
	<!-- 댓글을 등록할 때 포스트 번호를 hidden으로 전달하여 해당 댓글을 어느 포스트에 등록할지 지정한다. -->
	<div class="container mt-3">
		<input type="hidden" id="postId" value="${ post.id }">
	</div>
	<table class="table">
		<thead>
			<tr>
				<th><h4>댓글 목록</h4></th>
			</tr>
		</thead>
		<tbody>
			<tr align="right">
				<td>
					<textarea id="reply-content" rows="1" class="form-control"></textarea>
					<button id="btn-save-reply" class="btn btn-secondary">댓글 등록</button>
				</td>
			</tr>
		</tbody>
	</table>
	
</div>

<script src="/js/post.js"></script>

<%@ include file="../layout/footer.jsp" %>