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
	<c:if test="${! empty post.replyList }">
		<div class="container mt-3">
			<table>
				<thead>
					<tr>
						<th width="80%">내용</th>
						<th width="10%">작성자</th>
						<th width="10%">삭제</th>
					</tr>
				</thead>
				<tbody>
					<!--
						댓글 목록 화면을 구성하기 위해서는 JSP파일이 실행되기 바로 직전에 JSP파일에서
						사용할 데이터가 Model에 등록되어 있어야 한다. 이는 컨트롤러에서 진행되어야 할 부분인데,
						ReplyController 클래스와 PostController 클래스 어디에도 댓글 목록을 조회하는 메소드를 제공하지 않는다.
						그럼에도 댓글 목록을 출력할 수 있는 이유는 앞에서 Reply와 Post를 양방향으로 매핑했기 때문이다.
					-->
					<c:forEach var="reply" items="${ post.replyList }">
						<tr>
							<td>${ reply.content }</td>
							<td>${ reply.user.username }</td>
							<td><button>삭제</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
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
<script src="/js/reply.js"></script>

<%@ include file="../layout/footer.jsp" %>