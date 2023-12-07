<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- main.css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">

<!-- 캐러셀 -->
<div id="mainCarousel" class="carousel slide" data-bs-ride="carousel">
	<!-- 캐러셀 이미지 -->
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="/image/main/safetyLoader1.png" class="d-block w-100">
		</div>
		<div class="carousel-item">
			<img src="/image/main/safetyLoader2.png" class="d-block w-100">
		</div>
	</div>
</div>

<div class="container">


	<c:if test="${empty post}">
		<h1>게시글 작성자와 관리자만 확인할 수 있습니다.</h1>
	</c:if>
	
	<c:if test="${!empty post }">
		<!-- 메인 컨텐츠 -->
		<div class="col-md-12 p-3">
			<div class="mainBorder p-3">
			
				<!-- 제목 -->
				<div class="row">
					<div class="col-12 mb-3"><h5><b>제목</b></h5></div>
				</div>
				<div class="row">
					<div class="col-12 mb-3">
						<input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요.">
					</div>
				</div>
				
				<!-- 차종 -->
				<div class="row">
					<div class="col-12 mb-3"><h5><b>차종 영역</b></h5></div>
				</div>
				<div class="row">
					<div class="col-12 mb-3">
						<input type="text" class="form-control" id="cartype" placeholder="차종을 입력해주세요.">
					</div>
				</div>
				
				<!-- 출발지 설정 -->
				<div class="row">
					<div class="col-12 mb-3"><h5><b>출발지 주소</b></h5></div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-md-3 mb-3">
						<input type="text" class="form-control" id="departures_postcode" placeholder="출발지 우편번호">
					</div>
					<div class="col-sm-6 col-md-3 mb-3">
						<input type="button" class="form-control btn btn-secondary" onclick="departuresSetting()" value="우편번호 찾기">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-10 mb-3">
						<input type="text" class="form-control" id="departures_address" placeholder="출발지 주소">
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-sm-12 col-md-6 mb-3">
						<input type="text" class="form-control" id="departures_detailAddress" placeholder="출발지 상세주소">
					</div>
					<div class="col-sm-12 col-md-4 mb-3">
						<input type="text" class="form-control" id="departures_extraAddress" placeholder="출발지 참고항목">
					</div>
				</div>
				
				<!-- 도착지 설정 -->
				<div class="row mb-3">
					<div class="col-12"><h5><b>도착지 주소</b></h5></div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-md-3 mb-3">
						<input type="text" class="form-control" id="arrivals_postcode" placeholder="도착지 우편번호">
					</div>
					<div class="col-sm-6 col-md-3 mb-3">
						<input type="button" class="form-control btn btn-secondary" onclick="arrivalsSetting()" value="우편번호 찾기">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-10 mb-3">
						<input type="text" class="form-control" id="arrivals_address" placeholder="도착지 주소">
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-sm-12 col-md-6 mb-3">
						<input type="text" class="form-control" id="arrivals_detailAddress" placeholder="도착지 상세주소">
					</div>
					<div class="col-sm-12 col-md-4 mb-3">
						<input type="text" class="form-control" id="arrivals_extraAddress" placeholder="도착지 참고항목">
					</div>
				</div>
				
				<!-- 차 사진 등록 -->
				<div class="row mb-3">
					<div class="col-2"><h5><b>첨부파일</b></h5></div>
					<div class="col-6" align="left">
						<button class="btn btn-success" type="button" onclick="addFile();"><span>파일 추가</span></button>
					</div>
				</div>
				<div class="row">
					<div class="col-12 mb-3">
						<div id="file_list">
		                    <div class="row">
		                    	<div class="col-md-5 col-sm-12 mb-3">
		                            <input class="form-control files" type="file" name="files" onchange="selectFile(this);" />
		                    	</div>
		                    	<div class="col-md-7 col-sm-12 mb-3" align="left">
		                    		<button class="btn btn-danger" type="button" onclick="removeFile(this);"><span>삭제</span></button>
		                    	</div>
		                    </div>
			            </div>
					</div>
				</div>
				
				<!-- 내용 -->
				<div class="row mb-3">
					<div class="col-12"><h5><b>내용</b></h5></div>
				</div>
				<div class="mb-3">
					<textarea class="form-control" rows="5" id="content" placeholder="내용을 입력해주세요."></textarea>
				</div>
				
				<input type="hidden" id="postid" value="${ post.postid }">
				<div class="mb-1">
					<c:if test="${ post.state == 'R' }">
	      				<span style="background-color: red; border-color: red; border-style: solid; border-radius: 20%; color: white;">대기</span>
	      			</c:if>
	      			<c:if test="${ post.state == 'P' }">
	      				<span style="background-color: orange; border-color: orange; border-style: solid; border-radius: 20%; color: white;">처리</span>
	      			</c:if>
	      			<c:if test="${ post.state == 'C' }">
	      				<span style="background-color: green; border-color: green; border-style: solid; border-radius: 20%; color: white;">완료</span>
	      			</c:if>
	      			&nbsp;<b>${ post.title }</b>
				</div>
				<div class="mb-1">
					<!-- 날짜 데이터의 숫자가 소수점으로 표시되어서 Format변경 -->
		        	<fmt:parseDate value="${ post.regdate }" var="dateFormat" pattern="yyyy-MM-dd HH:mm:ss"/>
					<fmt:formatDate value="${ dateFormat }" var="regdate" pattern="yyyy-MM-dd HH:mm:ss"/>
	      			${ post.name }&nbsp;│&nbsp;${ regdate }
				</div>
				<hr>
				<div>
					${ post.content }
				</div>
				<button class="btn btn-danger" id="btn-delete">삭제</button>
				
				
				
				
			</div>
		</div>
	</c:if>
	
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
</div>

<script src="/js/post.js"></script>

<%@ include file="../layout/footer.jsp" %>