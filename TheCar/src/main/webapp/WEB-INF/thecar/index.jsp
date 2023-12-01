<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp"%>

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

<div class="container mt-3" style="min-height: 500px;">
	
	<div class="row">
	
		<!-- 메인 컨텐츠 -->
		<div class="col-md-9 col-sm-12 p-3">
			<div class="mainBorder p-3">
			
				<!-- 게시글 -->
				<div class="row">
					<div class="col-lg-2 col-md-12">
						<h2 style="font-weight: bold;"><a href="/" style="text-decoration: none; color: #333333;">전체글</a></h2>
					</div>
					<c:if test="${ principal.user.role == 'Admin' }">
						<!-- 관리자 검색 기능 -->
						<div class="col-lg-7 col-md-12 mb-3">
							<div class="input-group">
								<div class="col-3">
									<select class="form-select col-2" style="border-radius: 0px;">
										<option>제목</option>
										<option>제목+내용</option>
										<option>글쓴이</option>
									</select>
								</div>
								<div class="col-7">
								    <input type="text" class="form-control" style="border-radius: 0px;" placeholder="내용">
								</div>
								<div class="col-2">
							  		<button class="btn btn-success" type="submit" style="border-radius: 0px;">검색</button>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${ principal.user.role != 'Admin' }">
						<div class="col-lg-7 col-md-12 mb-3"></div>
					</c:if>
					<c:if test="${!empty principal }">
						<div class="col-lg-3 col-md-12 mb-3" style="text-align: center;">
							<a class="col btn btn-dark" href="/post/mypost">내가쓴글</a>&nbsp;&nbsp;
							<a class="col btn btn-dark" href="/post/insertPost">글쓰기</a>
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
							        	<td style="text-align: center;">${ post.postid }</td>
							        	<td>
								        	<!-- 게시글 제목 -->
							        		<a href="post/${ post.postid }" style="text-decoration: none; color: black;">
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
				
				<!-- 페이지네이션 -->
				<div class="row">
					<div class="col-12">
						<ul class="pagination justify-content-center">
							<!-- 앞쪽 페이지 이동 -->
							<c:if test="${ startPage > 10 }">
								<!-- 첫번째 페이지 번호로 이동 -->
								<li class="page-item">
									<a class="page-link" href="?start=0" style="color: #666666;"><i class="bi bi-chevron-double-left"></i></a>
								</li>
								<!-- 이전 페이지 영역의 마지막 페이지 번호로 이동 -->
								<li class="page-item">
									<a class="page-link" href="?start=${ ( startPage - 2 ) * searchPage.size }" style="color: #666666;"><i class="bi bi-chevron-left"></i></a>
								</li>
							</c:if>
						
						
							<!-- 페이지 생성 -->
							
							<!-- 남은 페이지 계산 -->
							<c:if test="${ lastPage - startPage >= 10 }">
								<!-- 남은 페이지가 10개보다 많으면 10개를 출력 -->
								<c:forEach var="page" begin="${ startPage }" end="${ startPage + 9 }">
										<li class="page-item">
											<c:if test="${ nowPage == page }">
												<a class="page-link" href="?start=${ searchPage.size * (page - 1) }" style="color: black;"><b>${ page }</b></a>
											</c:if>
											<c:if test="${ nowPage != page }">
												<a class="page-link" href="?start=${ searchPage.size * (page - 1) }" style="color: #666666;">${ page }</a>
											</c:if>
										</li>
								</c:forEach>
							</c:if>
							<c:if test="${ lastPage - startPage < 10 }">
								<!-- 남은 페이지가 10개 이하면 lastPage까지 출력 -->
								<c:forEach var="page" begin="${ startPage }" end="${ lastPage }">
									<li class="page-item">
										<c:if test="${ nowPage == page }">
											<a class="page-link" href="?start=${ searchPage.size * (page - 1) }" style="color: black;"><b>${ page }</b></a>
										</c:if>
										<c:if test="${ nowPage != page }">
											<a class="page-link" href="?start=${ searchPage.size * (page - 1) }" style="color: #666666;">${ page }</a>
										</c:if>
									</li>
								</c:forEach>
							</c:if>
							
							
							
							<!-- 뒤쪽 페이지 이동 -->
							<c:if test="${ lastPage - startPage >= 10 }">
								<!-- 다음 페이지 영역의 첫번째 페이지 번호로 이동 -->
								<li class="page-item">
									<a class="page-link" href="?start=${ (startPage + 9) * searchPage.size }" style="color: #666666;"><i class="bi bi-chevron-right"></i></a>
								</li>
								<!-- 마지막 페이지 번호로 이동 -->
								<li class="page-item">
									<a class="page-link" href="?start=${ (lastPage - 1) * searchPage.size }" style="color: #666666;"><i class="bi bi-chevron-double-right"></i></a>
								</li>
							</c:if>
							
						</ul>
					</div>
				</div>
				
				
				
				
				
				
			</div>
			
		</div>
		
		<!-- 메인 컨텐츠 끝 -->
		
		<div class="col-md-3 col-sm-12 p-3">
			
			<!-- 전화번호 -->
			<div class="col-12 mb-2">
				<div class="mainBorder p-3">
					<h3 style="font-weight: bold;"><i class="bi bi-telephone-fill"></i>전화문의</h3>
					<hr/>
					<h2 style="font-weight: bold; color: orange; text-align: center;">1599-5866</h2>
					<h6 style="text-align: center;">업무시간: 24시간 근무</h6>
				</div>
			</div>
			
			<!-- 지도 -->
			<div class="col-12 mb-2">
				<div class="mainBorder p-3">
					<div id="map" style="width:100%;height:200px;"></div>
						<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${ kakaoMapKey }"></script>
						<script>
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
						    mapOption = { 
						        center: new kakao.maps.LatLng(37.503829110194516, 126.8766751494623), // 지도의 중심좌표
						        level: 3 // 지도의 확대 레벨
						    };
						
						var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
						
						// 마커가 표시될 위치입니다 
						var markerPosition  = new kakao.maps.LatLng(37.503829110194516, 126.8766751494623); 
						
						// 마커를 생성합니다
						var marker = new kakao.maps.Marker({
						    position: markerPosition
						});
						
						// 마커가 지도 위에 표시되도록 설정합니다
						marker.setMap(map);
						
						// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
						// marker.setMap(null);   
						</script>
				</div>
			</div>
			
			<!-- 전화번호 -->
			<div class="col-12 mb-2">
				<div class="mainBorder p-3">
					<h4 style="font-weight: bold; text-align: center;"><a href="https://github.com/Arsyux" style="text-decoration: none; color: black;" target="_blank"><i class="bi bi-github"></i> Arsyux Git Hub</a></h4>
				</div>
			</div>
		</div>
		
	</div>
</div>


<%@ include file="./layout/footer.jsp" %>