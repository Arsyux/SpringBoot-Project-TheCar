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

	<!-- 메인 컨텐츠 -->
	<div class="row">
		<div class="col-md-12 p-3">
			<c:if test="${empty post}">
				<div class="mainBorder p-3">
					<div class="row">
						<div class="col-12 mb-3">
							<h1>게시글 작성자와 관리자만 확인할 수 있습니다.</h1>
						</div>
					</div>
					<div class="row">
						<div class="col-2">
							<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${!empty post }">
				<div class="mainBorder p-3">
				
					<input type="hidden" id="postid" value="${ post.postid }">
					
					<div class="row">
						<div class="col-12 mb-3">
							<!-- 상태 -->
							<c:if test="${ post.state == 'R' }">
			      				<span style="background-color: red; border-color: red; border-style: solid; border-radius: 20%; color: white;">대기</span>
			      			</c:if>
			      			<c:if test="${ post.state == 'P' }">
			      				<span style="background-color: orange; border-color: orange; border-style: solid; border-radius: 20%; color: white;">처리</span>
			      			</c:if>
			      			<c:if test="${ post.state == 'C' }">
			      				<span style="background-color: green; border-color: green; border-style: solid; border-radius: 20%; color: white;">완료</span>
			      			</c:if>
			      			&nbsp;
			      			<!-- 지역 간략하게 표시 -->
			      			<span style="color: #BF9000">[${ fn:substring(post.departures_address,0, 2) }
		        			->
		        			${ fn:substring(post.arrivals_address,0, 2) }]</span>
							<!-- 제목 -->
			      			&nbsp;<b>${ post.title }</b>
			      			<hr>
						</div>
					</div>
					
					<div class="row">
						<div class="col-12">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td align="center" width="20%">이름</td>
										<td align="center">${ post.name }</td>
									</tr>
									<tr>
										<td align="center" width="20%">휴대폰</td>
										<td align="center">${ post.phone }</td>
									</tr>
									<tr>
										<td align="center" width="20%">작성 날짜</td>
										<!-- 날짜 데이터의 숫자가 소수점으로 표시되어서 Format변경 -->
							        	<fmt:parseDate value="${ post.regdate }" var="dateFormat" pattern="yyyy-MM-dd HH:mm:ss"/>
										<fmt:formatDate value="${ dateFormat }" var="regdate" pattern="yyyy-MM-dd HH:mm:ss"/>
										<td align="center">${ regdate }</td>
									</tr>
									<tr>
										<td align="center" width="20%">차종</td>
										<td align="center">${ post.cartype }</td>
									</tr>
									<tr>
										<td align="center" width="20%">출발지 주소</td>
										<td align="center">
											<span>[${ post.departures_postcode }] 
											${ post.departures_address } ${ post.departures_detailAddress } ${ post.departures_extraAddress }
											</span> 
										</td>
									</tr>
									<tr>
										<td align="center" width="20%">도착지 주소</td>
										<td align="center">
											<span>[${ post.arrivals_postcode }] 
											${ post.arrivals_address } ${ post.arrivals_detailAddress } ${ post.arrivals_extraAddress }
											</span> 
										</td>
									</tr>
									<tr>
										<td align="center" width="20%">내용</td>
										<td align="center">
											<span>${ post.content }</span> 
										</td>
									</tr>
									<tr>
										<td align="center" width="20%">첨부파일</td>
										<td align="center">
											<c:forEach var="file" items="${ post.files }">
												<fmt:parseDate value="${ post.regdate }" var="dateFormat" pattern="yyyy-MM-dd HH:mm:ss"/>
												<fmt:formatDate value="${ dateFormat }" var="fileRegdate" pattern="yyMMdd"/>
												<img class="w-100 mb-1" src="/image/postImage/${ fileRegdate }/${ file.save_name }" style="max-width: 240px;">
											</c:forEach>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-2">
							<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
						</div>
						<div class="col-10" align="right">
							<c:if test="${ principal.user.role == 'Admin' || principal.user.userid == post.userid  }">
								<a class="btn btn-primary" href="/post/updatePost/${ post.postid }">수정</a>
								<button class="btn btn-danger" id="btn-delete">삭제</button>
							</c:if>
						</div>
					</div>
					
				</div>
			</c:if>
		</div>
	</div>
	
</div>

<script src="/js/post.js"></script>

<%@ include file="../layout/footer.jsp" %>