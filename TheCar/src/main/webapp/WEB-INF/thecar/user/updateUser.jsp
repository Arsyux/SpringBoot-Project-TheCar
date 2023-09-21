<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br>
<div class="container mt-3">

	<!--
		header.jsp 파일에서 선언한 principal 변수를 이용하여 세션에 저장된 회원 상세 정보를 출력한다.
		id는 hidden으로 지정하고 username과 email 정보만 보이도록 한다.
		보안상 password는 보여주면 안 되기 때문에 출력하지 않는다.
	-->
	<form>
		<input type="hidden" id="id" value="${ principal.user.id }">
		<div class="mb-3">
			<label for="uname">Username:</label>
			<input type="text" class="form-control" id="username" value="${ principal.user.username }">
		</div>
		<div class="mb-3">
			<label for="password">Password:</label>
			<input type="password" class="form-control" id="password" placeholder="Enter password">
		</div>
		<div class="mb-3">
			<label for="email">Email:</label>
			<input type="email" class="form-control" id="email" value="${ principal.user.email }">
		</div>
	</form>
	<button id="btn-update" class="btn btn-secondary">회원 정보 수정</button>
	
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>