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
				<div class="row">
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
							        			<span style="background-color: green; border-color: green; border-style: solid; border-radius: 20%; color: white;">OK</span> ${ post.title }
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
						    	<tr>
						        	<td>NoData</td>
						        	<td>Doe</td>
						        	<td>john@example.com</td>
					        	</tr>
					        	<tr>
						      		<td>Mary</td>
						      		<td>Moe</td>
						      		<td>mary@example.com</td>
					      		</tr>
					    	</c:if>
					    </tbody>
					</table>
				</div>
				
				<div class="row">
					<div class="col-10"></div>
					<c:if test="${!empty principal }">
						<div class="col-2"><a class="btn btn-primary" href="post/insertPost">작성</a></div>
					</c:if>
				</div>
				
				
				
				
			</div>
		</div>
		
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
			<div class="col-12">
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
		</div>
		
	</div>
</div>


<%@ include file="./layout/footer.jsp" %>