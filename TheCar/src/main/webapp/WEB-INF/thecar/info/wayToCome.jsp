<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

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

<!-- 오시는길 -->
<div class="container mt-4">

	<div class="row mb-5">
		<div class="col-sm-12 col-md-2">
			<h4 style="color: #666666; min-width: 100px;">회사소개</h4>
		</div>
		<div class="col-sm-0 col-md-10" style="font-weight: bold; color: #666666">
			<hr style="max-width: 100%">
		</div>
	</div>
	<div class="row mb-3">
		<h5 style="color: #333333">오시는길</h5>
		<hr style="color: #333333">
	</div>
	
	<div class="row mb-5">
		<div class="col-12">
		
			<div id="map" style="width:100%;height:500px;"></div>

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
	<div class="row">
		<div class="col-12">
			<div>▶&nbsp;회사명&nbsp;&nbsp;&nbsp;더카탁송</div>
			<div>▶&nbsp;TEL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1599-5866</div>
			<div>▶&nbsp;FAX&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;02) 2692-3654</div>
			<div>▶&nbsp;주소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;서울시 구로구 경인로 55길 58, 구로밸리온 1005호</div>
		</div>
	</div>
	
</div>

<%@ include file="../layout/footer.jsp" %>