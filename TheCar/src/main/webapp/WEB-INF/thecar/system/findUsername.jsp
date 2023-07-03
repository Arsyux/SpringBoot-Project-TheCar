<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- login.css -->
<link rel="stylesheet" type="text/css" href="/css/login.css">

<div class="container mt-5 mb-5">

	<!-- Logo -->
	<div class="row">
		<div class="col-sm-0 col-md-4"></div>
		<div class="col-sm-12 col-md-4">
			<img class="w-100" src="/image/main/logo.png">
		</div>
		<div class="col-sm-0 col-md-4"></div>
	</div>

	<h1 class="mt-3" style="text-align: center; font-weight: bold; color: #333333">아이디 찾기</h1>

	<!-- 영역 제한 -->
	<div class="row mt-5">
		<div class="col-sm-0 col-md-2 col-lg-4"></div>
		<div class="col-sm-0 col-md-8 col-lg-4">

			<div class="row mb-3 pb-3" style="border-style: solid; border-color: #DDDDDD; border-radius: 5px;">
				<!-- 휴대전화번호 -->
				<div class="col-8 mt-3">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-telephone"></i></span> <input type="text" class="form-control" id="phonenumber" placeholder="휴대전화번호 (-제외)"></input>
						<div id="phonenumberInvalid" class="invalid-feedback"></div>
					</div>
				</div>
				<div class="col-4 mt-3">
					<a id="btn-phoneCheck" class="btn btn-secondary w-100" href="#">인증요청</a>
				</div>

				<!-- 인증번호 -->
				<div class="col-12 mt-3">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-key"></i></span> <input type="text" class="form-control" id="phonenumberCheck" placeholder="인증번호"></input>
					</div>
				</div>

				<div class="col-12 mt-3">
					<button id="btn-phonCheckOK" class="btn btn-secondary btn-large w-100 disabled">인증확인</button>
				</div>
				<div id="findUsername" class="col-12 mt-3" style="text-align: center; display: none;"></div>
			
			</div>


			<div class="row mt-3">
				<div class="col-12" style="text-align: center;">
					<a class="logintag" href="/auth/findUsername" style="border-right: solid; border-width: 1px; border-color: #555555; padding-right: 10px; padding-left: 10px;">아이디 찾기</a> <a class="logintag"
						href="/auth/findPassword" style="border-right: solid; border-width: 1px; border-color: #555555; padding-right: 10px; padding-left: 10px;">비밀번호 찾기</a> <a class="logintag" href="/auth/insertUser"
						style="padding-right: 10px; padding-left: 10px;">회원가입</a>
				</div>
			</div>
		</div>

		<div class="col-sm-0 col-md-2 col-lg-4"></div>

	</div>

</div>

<script src="/js/findUser.js"></script>

<%@ include file="../layout/footer.jsp"%>