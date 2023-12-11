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
				<h2 style="color: crimson;"><b>회원탈퇴</b></h2>
				<hr>
				<div class="row mb-1">
					<div class="col-12" align="center">
						<textarea class="form-control" rows="9" readonly="readonly" style="background-color: white;">
제1조 (회원탈퇴에 따른 제한)
“회원”이 탈퇴하는 경우, “회원 “이 보유한 쿠폰, 마일리지는 모두 삭제됩니다.

제2조 (개인정보 및 위치정보 보유)
“회원”이 이용계약을 해지하는 경우, “회사”는 관련법령 및 개인정보처리방침에 따라 “회원”의 개인정보 등을 보유하는 경우를 제외하고 해지 즉시 “회원”의 모든 정보를 삭제합니다. 다만 “회원”이 탈퇴하거나 자격을 상실할 경우에도 불구하고, “회사”는 [회사 내부 방침], [관계법령]에 따라 아래의 기간동안 개인정보 및 위치정보를 보유할 수 있습니다.

부칙
제1조 (시행일) 이 약관은 2023년 12 월 11 일부터 시행합니다.
						</textarea>
					</div>
				</div>
				
				<div class="row mb-3">
					<div class="col-12" align="left">
						<input onclick='getCheckboxValue(event)' class="form-check-input" type="checkbox" name="remember"> 확인하였습니다.
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-12" align="center">
						<button id="btn-delete" class="btn btn-danger" disabled="disabled">회원탈퇴</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>

<script>
function getCheckboxValue(event)  {
	if(event.target.value == 'on'){
		document.getElementById("btn-delete").disabled = false;
	}
}
</script>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>