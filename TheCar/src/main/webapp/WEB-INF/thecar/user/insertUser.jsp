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
	
	<div class="row mt-5">
		<div class="col-sm-0 col-md-4"></div>

		<div class="col-sm-12 col-md-4">
			<form action="/auth/securitylogin" method="post">

				<div class="row mb-3" style="border-style: solid; border-color: #DDDDDD; border-radius: 5px; border-width: 1px;">
					<div class="col-12">
						<!-- ID -->
						<div class="input-group mt-3 mb-3">
							<span class="input-group-text"><i class="bi bi-person"></i></span> <input type="text" class="form-control" name="userid" placeholder="아이디"></input>
						</div>

						<!-- Password -->
						<div class="input-group mb-3">
							<span class="input-group-text"><i class="bi bi-lock"></i></span> <input type="password" class="form-control" name="password" placeholder="비밀번호"></input>
						</div>

					</div>
				</div>

				<!-- 휴대전화 인증 -->
				<div class="row mb-3" style="border-style: solid; border-color: #DDDDDD; border-radius: 5px; border-width: 1px;">
					<div class="col-12">

						<!-- 이름 -->
						<div class="input-group mt-3 mb-3">
							<span class="input-group-text"><i class="bi bi-person"></i></span> <input type="text" class="form-control" name="username" placeholder="이름"></input>
						</div>

						<!-- 생년월일 -->
						<div class="input-group mt-3 mb-3">
							<span class="input-group-text"><i class="bi bi-calendar-event"></i></span> <input type="text" class="form-control" name="username" placeholder="생년월일 8자리"></input>
						</div>

						<!-- 성별 -->
						<div class="row">
							<div class="col-6">
								<div class="row">
									<div class="btn-group">
										<label class="btn" style="border: solid; border-width: 1px; border-color: #ced4da;"> <input type="radio" id="jb-radio-1" checked="checked">남자
										</label> <label class="btn" style="border: solid; border-width: 1px; border-color: #ced4da;"> <input type="radio" id="jb-radio-2">여자
										</label>
									</div>
								</div>
							</div>

							<!-- 내/외국인 -->
							<div class="col-6">
								<div class="row">
									<div class="btn-group">
										<label class="btn" style="border: solid; border-width: 1px; border-color: #ced4da;"> <input type="radio" id="jb-radio-3" checked="checked">내국인
										</label> <label class="btn" style="border: solid; border-width: 1px; border-color: #ced4da;"> <input type="radio" id="jb-radio-4">외국인
										</label>
									</div>
								</div>
							</div>
						</div>

						<!-- 통신사 -->
						<div class="input-group mt-3">
							<div class="dropdown" style="width: 100%;">
								<button id="select-dropdown" type="button" class="btn dropdown-toggle w-100" data-bs-toggle="dropdown" style="border: solid; border-width: 1px; border-color: #ced4da;">통신사 선택</button>
								<ul class="dropdown-menu" style="width: 100%;">
									<li><a class="dropdown-item" href="#" onclick="test(this)" style="text-align: center;">SKT</a></li>
									<li><a class="dropdown-item" href="#" onclick="test(this)" style="text-align: center;">KT</a></li>
									<li><a class="dropdown-item" href="#" onclick="test(this)" style="text-align: center;">LG U+</a></li>
									<li><a class="dropdown-item" href="#" onclick="test(this)" style="text-align: center;">SKT 알뜰폰</a></li>
									<li><a class="dropdown-item" href="#" onclick="test(this)" style="text-align: center;">KT 알뜰폰</a></li>
									<li><a class="dropdown-item" href="#" onclick="test(this)" style="text-align: center;">LG U+ 알뜰폰</a></li>
								</ul>
							</div>
						</div>

						<!-- 휴대전화번호 -->
						<div class="row">
							<div class="col-9">
								<div class="input-group mt-3">
									<span class="input-group-text"><i class="bi bi-telephone"></i></span> <input type="text" class="form-control" name="username" placeholder="휴대전화번호"></input>
								</div>
							</div>
							<div class="col-3 mt-3">
								<button id="#" class="btn btn-secondary w-100">인증</button>
							</div>
						</div>

						<!-- 인증번호 -->
						<div class="input-group mt-3 mb-3">
							<span class="input-group-text"><i class="bi bi-key"></i></span> <input type="text" class="form-control" name="username" placeholder="인증번호"></input>
						</div>
					</div>
				</div>

			</form>

			<div class="row mb-3">
				<div class="col-12">
					<button id="btn-save" class="btn btn-secondary btn-large w-100">회원가입</button>
				</div>
			</div>

		</div>

	</div>

	<div class="col-sm-0 col-md-4"></div>
</div>


<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>