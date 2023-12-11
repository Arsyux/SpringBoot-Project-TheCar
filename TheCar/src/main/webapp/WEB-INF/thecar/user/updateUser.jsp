<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- main.css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">

<div class="container mt-3">

	<div class="row">
		<!-- 글 작성하기 -->
		<div class="col-sm-12 col-md-2 p-3">
			<div class="mainBorder p-3">
				<div class="row mb-3">
					<div class="col-12">
						<a class="loginTag" href="/user/updateUser">회원 정보 변경</a>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<a class="loginTag" href="/user/deleteUser">회원 탈퇴</a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12 col-md-8 p-3">
			<div class="mainBorder p-3">
				<h2><b>회원정보 변경</b></h2>
				<hr>
				<div class="row mb-3">
					<!-- Password -->
					<div class="col-12 mt-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-lock"></i></span> <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호"></input>
							<div id="passwordInvalid" class="invalid-feedback"></div>
						</div>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-12" align="center">
						<button id="btn-update" class="btn btn-secondary">비밀번호 변경</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>