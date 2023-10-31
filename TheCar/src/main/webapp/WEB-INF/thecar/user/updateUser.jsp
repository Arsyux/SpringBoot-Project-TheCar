<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<br>
<div class="container mt-3">

	<form>
		<input type="hidden" id="id" value="${ principal.user.id }">
		
		<!-- Password -->
		<div class="col-12 mt-3">
			<div class="input-group">
				<span class="input-group-text"><i class="bi bi-lock"></i></span> <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호"></input>
				<div id="passwordInvalid" class="invalid-feedback"></div>
			</div>
		</div>
		
		<!-- 이메일 -->
		<div class="col-12 mt-3 mb-3">
			<div class="input-group">
				<span class="input-group-text"><i class="bi bi-at"></i></span>
					<input type="email" class="form-control" name="email" id="email" placeholder="이메일" value="${ principal.user.email }"></input>
				<div id="emailInvalid" class="invalid-feedback"></div>
			</div>
		</div>
		
		<!-- 포인트 -->
		<div class="col-12 mt-3 mb-3">
			<div class="input-group">
				<span class="input-group-text"><i class="bi bi-piggy-bank"></i></span>
					<input type="text" class="form-control" name="point" id="point" value="${ principal.user.point }"></input>
			</div>
		</div>
	</form>
	
	<button id="btn-update" class="btn btn-secondary">회원 정보 수정</button>
	
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>