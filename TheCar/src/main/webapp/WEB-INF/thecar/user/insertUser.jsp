<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br>
<div class="container mt-5 mb-5">

	<!-- Logo -->
	<div class="row">
		<div class="col-sm-0 col-md-4"></div>
		<div class="col-sm-12 col-md-4">
			<img class="w-100" src="/image/main/logo.png">
		</div>
		<div class="col-sm-0 col-md-4"></div>
	</div>

	<!-- 입력창 -->
	<div class="row mt-5">
		<div class="col-sm-0 col-md-4"></div>


		<div class="col-sm-12 col-md-4">

			<form action="/auth/securitylogin" method="post">
				<div class="row mb-3" style="border-style: solid; border-color: #DDDDDD; border-radius: 5px; border-width: 1px;">

					<!-- ID -->
					<div class="col-12 mt-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-person"></i></span>
							<input type="text" class="form-control" name="username" id="username" placeholder="아이디"></input>
							<div id="usernameInvalid" class="invalid-feedback"></div>
						</div>
					</div>

					<!-- Password -->
					<div class="col-12 mt-3 mb-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-lock"></i></span> <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호"></input>
							<div id="passwordInvalid" class="invalid-feedback"></div>
						</div>
					</div>

				</div>
			</form>

			<div class="row mb-3">
				<div class="col-12">
					<button id="btn-insertUser" class="btn btn-dark btn-large w-100">회원가입</button>
				</div>
			</div>

		</div>

	</div>


	<div class="col-sm-0 col-md-4"></div>
</div>
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>