<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container mt-3 mb-3">

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
		
		<button id="btn-login" class="btn btn-secondary"><spring:message code="user.login.form.login_signup"/></button>
		
		<!-- 
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=ee0b7525aab13922c886f01538c0d6f6&redirect_uri=http://localhost:5000/oauth/kakao&response_type=code"><img height="38px" src="/image/loginbutton/kakao_login_btn.png"></a>
		-->
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=ee0b7525aab13922c886f01538c0d6f6&redirect_uri=http://thecar-env.eba-26kgis3m.ap-northeast-2.elasticbeanstalk.com/oauth/kakao&response_type=code"><img height="38px" src="/image/loginbutton/kakao_login_btn.png"></a>
		<a href="../oauth2/authorization/google"><img height="38px" src="/image/loginbutton/google_login_btn.png"></a>
		<!-- 
		<a href="../oauth2/authorization/google"><img height="38px" src="/image/loginbutton/google_login_btn.png"></a>
		-->
	</form>
	
</div>


<%@ include file="../layout/footer.jsp"%>