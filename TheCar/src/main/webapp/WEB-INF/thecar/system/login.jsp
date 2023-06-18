<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container mt-3">
	<form action="/auth/securitylogin" method="post">
		<div class="mb-3">
			<label for="username">
				<spring:message code="user.login.form.username"></spring:message> :
			</label>
			<input type="text" class="form-control" name="username" placeholder="Enter username"></input>
		</div>
		<div class="mb-3">
			<label for="password">
				<spring:message code="user.login.form.password"></spring:message> :
			</label>
			<input type="password" class="form-control" name="password" placeholder="Enter password"></input>
		</div>

		<button id="btn-login" class="btn btn-secondary"><spring:message code="user.login.form.login_btn"/></button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=${ API_KEY }&redirect_uri=http://localhost:8080/oauth/kakao&response_type=code"><img height="38px" src="/image/kakao_login_btn.png"></a>
		<a href="../oauth2/authorization/google"><img height="38px" src="/image/google_login_btn.png"></a>
		
	</form>
</div>


<%@ include file="../layout/footer.jsp"%>