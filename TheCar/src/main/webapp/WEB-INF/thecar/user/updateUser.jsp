<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br>
<div class="container mt-3">

	<form>
		<input type="hidden" id="id" value="${ principal.user.id }">
		<div class="mb-3">
			<label for="uname">로그인 아이디</label>
			<input type="text" class="form-control" name="username" value="${ principal.user.username }">
		</div>
		<div class="mb-3">
			<label for="password">비밀번호</label>
			<input type="password" class="form-control" name="password" placeholder="Enter password">
		</div>
		<div class="mb-3">
			<label for="email">Email:</label>
			<input type="email" class="form-control" name="email" value="${ principal.user.email }">
		</div>
	</form>
	
	<button id="btn-update" class="btn btn-secondary">회원 정보 수정</button>
	
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>