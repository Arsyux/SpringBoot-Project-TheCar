<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br>
<div class="container mt-3">
	<!--
		스프링 시큐리티에 인증을 요청하기 위해 form 태그의 action 속성을 /auth/securitylogin로 지정하고 요청 방식은 post로 설정한다.
		여기서 중요한 것은 스프링 시큐리티가 사용자 정보를 추출할 때 id 속성을 인지하지 못하므로 기존의 id 속성 이름을 name으로 변경한다.	
	-->
	<form action="/auth/securitylogin" method="post">
		<div class="mb-3">
			<!--
				JSP 파일에서 다국어를 출력할 때는 spring:message 태그를 사용하며
				code 속성의 속성값으로 메시지 파일에 등록된 메시지의 키를 등록한다.
			-->
			<label for="uname"><spring:message code="user.login.form.username"/>:</label>
			<input type="text" class="form-control" placeholder="Enter username" name="username">
		</div>
		<div class="mb-3">
			<label for="pwd"><spring:message code="user.login.form.password"/>:</label>
			<input type="password" class="form-control" placeholder="Enter password" name="password">
		</div>

		<button id="btn-login" class="btn btn-secondary">
			<spring:message code="user.login.form.login_btn"/>
		</button>
	</form>
	
</div>

<!-- 스프링 시큐리티 적용에따라 주석 처리하였다.
<script src="/js/login.js"></script>
-->

<%@ include file="../layout/footer.jsp" %>