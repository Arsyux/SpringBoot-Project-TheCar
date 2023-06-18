<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br>
<br>
<div class="continer border">
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
		포스트 번호: <span id="id"><i>${ post.id }</i></span><br> 작성자: <span><i>${ post.user.username }</i></span>
	</div>

	<hr>
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<a href="/post/updatePost/${ post.id }" class="btn btn-warning">수정하기</a>
	<button id="btn-delete" class="btn btn-danger">삭제하기</button>
	<br> <br>
	<c:if test="${!empty post.replyList }">
		<div class="container mt-3">
			<table class="table">
				<thead>
					<tr>
						<th width="80%">내용</th>
						<th width="10%">작성자</th>
						<c:if test="${ reply.user.username != null && reply.user.username == principal.username }">
							<th width="10%">삭제</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<!-- 
				댓글 목록은 기존의 댓글 등록 화면 위에 출력하되 댓글 목록이 존재하는 경우에만 제공한다.
				댓글 목록 화면을 구성하기 위해서는 JSP 파일이 실행되기 바로 직전에 JSP 파일에서
				사용할 데이터가 Model에 등록되어 있어야 한다.
				이는 컨트롤러에서 진행되어야 할 부분인데, ReplyController 클래스와 PostController클래스
				어디에도 댓글 목록을 조회하는 메소드를 제공하지 않는다.
				그럼에도 댓글 목록을 출력할 수 있는 이유는 앞에서 Reply와 Post를 양방향으로 매핑했기 때문임.
				 -->
					<c:forEach var="reply" items="${ post.replyList }">
						<tr>
							<td>${ reply.content }</td>
							<td>${ reply.user.username }</td>
							<c:if test="${ reply.user.username != null && reply.user.username == principal.username }">
								<td><button onclick="replyObject.deleteReply(${ post.id }, ${ reply.id })">삭제</button></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
	<div class="container mt-3">
		<input type="hidden" id="postId" value="${ post.id }">
		<table class="table">
			<thead>
				<tr>
					<th><h4>댓글 목록</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr align="right">
					<td><textarea id="reply-content" rows="1" class="form-control"></textarea>
						<button id="btn-save-reply" class="btn btn-secondary">댓글 등록</button></td>
				</tr>
			</tbody>
		</table>
	</div>


</div>


<script src="/js/post.js"></script>
<script src="/js/reply.js"></script>
<%@ include file="../layout/footer.jsp"%>