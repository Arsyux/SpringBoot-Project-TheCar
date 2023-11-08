<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp"%>

<!-- main.css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">

<div class="container mt-3" style="min-height: 500px;">

	<div class="row">
		<!-- 메인 컨텐츠 -->
		<div class="col-md-9 col-sm-12 p-3">
			<div class="mainBorder p-3">
			
				<!-- 게시글 -->
				<div class="row">
					<div class="col-8">
						<h2 style="font-weight: bold;"><a href="test" style="text-decoration: none; color: #333333;">전체글</a></h2>
					</div>
					<c:if test="${!empty principal }">
						<div class="col-4" style="text-align: right;">
							<a class="btn btn-dark" href="test2">내가쓴글</a>&nbsp;&nbsp;
							<a class="btn btn-dark" href="post/insertPost">글쓰기</a>
						</div>
					</c:if>
					<hr/>
					<table class="table table-hover">
						<thead class="table-dark">
							<tr>
					        	<th class="col-1" style="text-align: center;">번호</th>
					        	<th class="col-6">제목</th>
					        	<th class="col-2" style="text-align: center;">작성자</th>
					        	<th class="col-3" style="text-align: center;">작성날짜</th>
				        	</tr>
					    </thead>
					    <tbody>
					    	<c:if test="${!empty postList }">
					    		<!-- 게시글이 있을 경우 -->
					    		<c:forEach var="post" items="${ postList }">
									<tr>
							        	<td style="text-align: center;">${ post.id }</td>
							        	<td>
								        	<!-- 게시글 제목 -->
							        		<a href="#" style="text-decoration: none; color: black;">
							        		<!-- 처리 상태에 따라서 다르게 표시 -->
							        			<c:if test="${ post.state == 'R' }">
							        				<span style="background-color: red; border-color: red; border-style: solid; border-radius: 20%; color: white;">대기</span>
							        			</c:if>
							        			<c:if test="${ post.state == 'P' }">
							        				<span style="background-color: orange; border-color: orange; border-style: solid; border-radius: 20%; color: white;">처리</span>
							        			</c:if>
							        			<c:if test="${ post.state == 'C' }">
							        				<span style="background-color: green; border-color: green; border-style: solid; border-radius: 20%; color: white;">완료</span>
							        			</c:if>
							        			&nbsp;&nbsp;${ post.title }
						        			</a>
						        		</td>
							        	<td style="text-align: center;">${ post.name }</td>
							        	<!-- 날짜 데이터의 숫자가 소수점으로 표시되어서 Format변경 -->
							        	<fmt:parseDate value="${ post.regdate }" var="dateFormat" pattern="yyyy-MM-dd HH:mm:ss"/>
										<fmt:formatDate value="${ dateFormat }" var="regdate" pattern="yyyy-MM-dd HH:mm:ss"/>
							        	<td style="text-align: center;">${ regdate }</td>
						        	</tr>
								</c:forEach>
								
								
					    	</c:if>
					    	<c:if test="${empty postList }">
					    		<!-- 게시글이 없을 경우 -->
					    		<c:forEach var="post" begin="1" end="${ searchPage.size }">
									<tr>
							        	<td height="41.5px;"> </td>
							        	<td></td>
							        	<td></td>
							        	<td></td>
						        	</tr>
								</c:forEach>
					    	</c:if>
					    </tbody>
					</table>
				</div>
				
				
				<a style="color: black;"><b>1</b></a>
				<a style="color: #666666;">2</a>
				<a style="color: #666666;">3</a>
				<a style="color: #666666;">4</a>
				
				<h1>현재 페이지 시작 : ${ searchPage.start }</h1>
				<h1>한번에 보이는 영역 : ${ searchPage.size }</h1>
				<h1>총 게시글 개수 : ${ searchPage.postCount }</h1>
				
				<!-- 총 페이지 계산 -->
				<c:if test="${ searchPage.postCount % searchPage.size != 0 || searchPage.postCount == 0}">
					<fmt:parseNumber integerOnly="true" var="lastPage" value="${ searchPage.postCount / searchPage.size + 1 }"></fmt:parseNumber>
				</c:if>
				<c:if test="${ searchPage.postCount % searchPage.size == 0 && searchPage.postCount != 0}">
					<fmt:parseNumber integerOnly="true" var="lastPage" value="${ searchPage.postCount / searchPage.size }"></fmt:parseNumber>
				</c:if>
				
				<!-- 현재 페이지 계산 -->
				<fmt:parseNumber integerOnly="true" var="nowPage" value="${ searchPage.start / searchPage.size + 1 }"></fmt:parseNumber>
				
				<!-- 시작 페이지 계산 -->
				<c:if test="${ nowPage % 10 != 0 }">
					<fmt:parseNumber integerOnly="true" var="startVal" value="${ nowPage / 10 }"></fmt:parseNumber>
					<fmt:parseNumber integerOnly="true" var="startPage" value="${ startVal * 10 + 1 }"></fmt:parseNumber>
				</c:if>
				<c:if test="${ nowPage % 10 == 0 }">
					<fmt:parseNumber integerOnly="true" var="startVal" value="${ nowPage / 10 - 1 }"></fmt:parseNumber>
					<fmt:parseNumber integerOnly="true" var="startPage" value="${ startVal * 10 + 1 }"></fmt:parseNumber>
				</c:if>
				
				
				
				<h3>시작 페이지 : ${ startPage }</h3>
				<h3>현재 페이지 : ${ nowPage } / ${ lastPage }</h3>
				
				
				
				
				<!-- 페이지네이션 -->
				<div class="row">
					<div class="col-12">
						<ul class="pagination justify-content-center">
							<!-- 앞쪽 페이지 이동 -->
							<c:if test="${ startPage > 10 }">
								<!-- 처음으로 -->
								<li class="page-item">
									<a class="page-link" href="test?start=0"><i class="bi bi-chevron-double-left"></i></a>
								</li>
								<!-- 이전 startPage의 마지막 번호로 이동 -->
								<li class="page-item">
									<a class="page-link" href="test?start=${ startPage * 9 * serchPage.size }"><i class="bi bi-chevron-left"></i></a>
								</li>
							</c:if>
						
						
						
							<c:forEach var="page" begin="1" end="${ lastPage }">
								<li class="page-item">
									<a class="page-link" href="test?start=${ searchPage.size * (page - 1) }">${ page }</a>
								</li>
							</c:forEach>
							
							
							
							<!-- 뒤쪽 페이지 이동 -->
							<c:if test="${ lastPage - startPage >= 10 }">
								<!-- 다음 페이지의 시작 페이지로 이동 -->	
								<!-- 에러 -->
								<li class="page-item">
									<a class="page-link" href="test?start=${ startPage + 10 * searchPage.size }"><i class="bi bi-chevron-right"></i></a>
								</li>
								
								<!-- 이전 startPage의 마지막 번호로 이동 -->
								<!-- OK -->
								<li class="page-item">
									<a class="page-link" href="test?start=${ (lastPage - 1) * searchPage.size }"><i class="bi bi-chevron-double-right"></i></a>
								</li>
							</c:if>
							
						</ul>
					</div>
				</div>
				
				
				<c:if test="${ principal.user.role == 'Admin' }" >
					<!-- 관리자 검색 기능 -->
					<div class="row mb-3">
						<div class="input-group">
							<div class="col-2"></div>
							<div class="col-2">
								<select class="form-select col-2" style="border-radius: 0px;">
									<option>제목</option>
									<option>제목+내용</option>
									<option>글쓴이</option>
								</select>
							</div>
							<div class="col-4">
							    <input type="text" class="form-control" style="border-radius: 0px;" placeholder="내용">
							</div>
							<div class="col-2">
						  		<button class="btn btn-success" type="submit" style="border-radius: 0px;">검색</button>
							</div>
							<div class="col-2"></div>
						</div>
					</div>
				</c:if>
				
				
				
			</div>
		</div>
	</div>
	
	
</div>


<%@ include file="./layout/footer.jsp" %>