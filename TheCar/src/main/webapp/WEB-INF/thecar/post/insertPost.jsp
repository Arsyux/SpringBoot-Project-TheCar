<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- main.css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">

<div class="container mt-3" style="min-height: 500px;">
	<div class="row">
		<!-- 글 작성하기 -->
		<div class="col-sm-12 p-3">
			<div class="mainBorder p-3">
				<h2>문의하기</h2>
				<hr>
				<form>
					<div class="mb-3">
						<input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요.">
					</div>
					<div class="mb-3">
						<input type="text" class="form-control" id="departures" placeholder="출발지">
						<br>
						<input type="text" id="sample6_postcode" placeholder="우편번호">
						<input type="button" onclick="departuresSetting()" value="우편번호 찾기"><br>
						<input type="text" id="sample6_address" placeholder="주소"><br>
						<input type="text" id="sample6_detailAddress" placeholder="상세주소">
						<input type="text" id="sample6_extraAddress" placeholder="참고항목">
					</div>
					<div class="mb-3">
						<input type="text" class="form-control" id="arrivals" placeholder="도착지">
						<br>
						<input type="text" id="sample7_postcode" placeholder="우편번호">
						<input type="button" onclick="arrivalsSetting()" value="우편번호 찾기"><br>
						<input type="text" id="sample7_address" placeholder="주소"><br>
						<input type="text" id="sample7_detailAddress" placeholder="상세주소">
						<input type="text" id="sample7_extraAddress" placeholder="참고항목">
					</div>
					<div class="mb-3">
						<h1>사진영역(20장)</h1>
					</div>
					<div class="mb-3">
						<textarea class="form-control" rows="5" id="content" placeholder="내용을 입력해주세요."></textarea>
					</div>
				</form>
				
				<button id="btn-insert" class="btn btn-secondary">포스트 등록</button>
					
					
					
					
			</div>
		</div>
	
	
	</div>
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
<!-- 서머노트 적용 -->
$(document).ready(function () {
	// <textarea> 태그의 id가 content
	$("#content").summernote({
		height: 400
	});
});
function departuresSetting() {
	new daum.Postcode({
		oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}
function arrivalsSetting() {
	new daum.Postcode({
		oncomplete: function(data) {
            var addr = '';
            var extraAddr = '';

            if (data.userSelectedType === 'R') { addr = data.roadAddress; }
            else { addr = data.jibunAddress; }

            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }

                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }

                document.getElementById("sample7_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample7_extraAddress").value = '';
            }

            document.getElementById('sample7_postcode').value = data.zonecode;
            document.getElementById("sample7_address").value = addr;
            document.getElementById("sample7_detailAddress").focus();
        }
    }).open();
}
</script>

<script src="/js/post.js"></script>

<%@ include file="../layout/footer.jsp" %>