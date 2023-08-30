<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp"%>

<div class="container-fluid mt-3">
	<div class="card">
	<c:forEach var="post" items="${ postList }">
		<div class="card-body">
			<h4 class="card-title">${ post.title }</h4>
			<a href="#" class="btn btn-secondary card-link">상세보기</a>
		</div>
	</c:forEach>
	</div>
</div>

<%@ include file="./layout/footer.jsp" %>