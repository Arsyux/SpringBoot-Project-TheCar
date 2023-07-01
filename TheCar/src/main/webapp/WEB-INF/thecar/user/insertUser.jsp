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

	<!-- 입력창 -->
	<div class="row mt-5">
		<div class="col-sm-0 col-md-4"></div>


		<div class="col-sm-12 col-md-4">

			<form action="/auth/securitylogin" method="post">
				<div class="row mb-3" style="border-style: solid; border-color: #DDDDDD; border-radius: 5px; border-width: 1px;">

					<!-- ID -->
					<div class="col-12 mt-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-person"></i></span> <input type="text" class="form-control" name="username" id="username" placeholder="아이디"></input>
						</div>
					</div>

					<!-- Password -->
					<div class="col-12 mt-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-lock"></i></span> <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호"></input>
						</div>
					</div>

					<!-- 이름 -->
					<div class="col-12  mt-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-person"></i></span> <input type="text" class="form-control" name="realname" id="realname" placeholder="이름"></input>
						</div>
					</div>

					<!-- 생년월일 -->
					<div class="col-12 mt-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-calendar-event"></i></span> <input type="text" class="form-control" name="birthdate" id="birthdate" placeholder="생년월일 8자리"></input>
						</div>
					</div>

					<!-- 성별 -->
					<div class="col-12 mt-3" style="text-align: center;">
						<div class="input-group">
							<div class="col-6 p-1" style="border: solid; border-width: 1px; border-radius: 0.25rem; border-color: #ced4da;">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoMan" value="man" checked="checked">
									<label class="form-check-label" for="inlineRadio1">남자</label>
								</div>
							</div>
							<div class="col-6 p-1" style="border: solid; border-width: 1px; border-radius: 0.25rem; border-color: #ced4da;">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdoWoman" value="woman">
									<label class="form-check-label" for="inlineRadio2">여자</label>
								</div>
							</div>
						</div>
					</div>

					<!-- 휴대전화번호 -->
					<div class="col-8 mt-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-telephone"></i></span> <input type="text" class="form-control" id="phonenumber" placeholder="휴대전화번호"></input>
						</div>
					</div>
					<div class="col-4 mt-3">
						<a id="btn-phoneCheck" class="btn btn-secondary w-100" href="#">인증요청</a>
					</div>

					<!-- 인증번호 -->
					<div class="col-12 mt-3 mb-3">
						<div class="input-group">
							<span class="input-group-text"><i class="bi bi-key"></i></span> <input type="text" class="form-control" id="phonenumberCheck" placeholder="인증번호"></input>
						</div>
					</div>
				</div>

			</form>

			<div class="row mb-3">
				<div class="col-12">
					<button id="btn-save" class="btn btn-secondary btn-large w-100 disabled">인증확인</button>
				</div>
			</div>

		</div>

	</div>


	<div class="col-sm-0 col-md-4"></div>
</div>


<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>