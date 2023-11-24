<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container mt-5 mb-5">

	<!-- Logo -->
	<div class="row">
		<div class="col-sm-0 col-md-4"></div>
		<div class="col-sm-12 col-md-4">
			<img class="w-100" src="/image/main/logo.png">
		</div>
		<div class="col-sm-0 col-md-4"></div>
	</div>

	<h3 align="center" style="font-weight: bold;">아이디 찾기</h3>

	<!-- 입력창 -->
	<div class="row mt-3">
		<div class="col-sm-0 col-md-4"></div>


		<div class="col-sm-12 col-md-4">

			<form>
				<div class="row mb-3" style="border-style: solid; border-color: #DDDDDD; border-radius: 5px; border-width: 1px;">
					
					<!-- 이름 -->
					<div class="col-12  mt-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-person-fill"></i></span> <input type="text" class="form-control" name="name" id="name" placeholder="이름"></input>
							<div id="nameInvalid" class="invalid-feedback"></div>
						</div>
					</div>
					
					<!-- 휴대전화번호 -->
					<div class="col-12 mt-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-telephone"></i></span>
								<input type="text" class="form-control" name="phone" id="phone" placeholder="휴대전화번호 (-제외)"></input>
							<div id="phoneInvalid" class="invalid-feedback"></div>
						</div>
					</div>
					
					<!-- 이메일 -->
					<div class="col-12 mt-3 mb-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-at"></i></span>
								<input type="email" class="form-control" name="email" id="email" placeholder="이메일"></input>
							<div id="emailInvalid" class="invalid-feedback"></div>
						</div>
					</div>
										
				</div>
			</form>

			<div class="row mb-3">
				<div class="col-12">
					<button id="btn-findUsername" class="btn btn-dark btn-large w-100">아이디 찾기</button>
				</div>
			</div>

		</div>

	</div>


	<div class="col-sm-0 col-md-4"></div>
</div>

<script src="/js/findUser.js"></script>

<%@ include file="../layout/footer.jsp" %>