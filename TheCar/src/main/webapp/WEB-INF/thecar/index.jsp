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
				<h1>Main</h1>
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