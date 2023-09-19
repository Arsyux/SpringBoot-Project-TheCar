<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br>
<div class="container mt-3">
	<form>
		<div class="mb-3">
			<!--
				JSP 파일에서 다국어를 출력할 때는 spring:message 태그를 사용하며
				code 속성의 속성값으로 메시지 파일에 등록된 메시지의 키를 등록한다.
			-->
			<label for="uname"><spring:message code="user.login.form.username"/>:</label>
			<input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
		</div>
		<div class="mb-3">
			<label for="pwd"><spring:message code="user.login.form.password"/>:</label>
			<input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
		</div>
	</form>
	<button id="btn-login" class="btn btn-secondary">
		<spring:message code="user.login.form.login_btn"/>
	</button>
	
</div>

<script src="/js/login.js"></script>

<%@ include file="../layout/footer.jsp" %>