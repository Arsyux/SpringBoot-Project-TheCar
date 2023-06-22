<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./layout/header.jsp"%>

<!-- Carousel -->
<div id="demo" class="carousel slide" data-bs-ride="carousel">

	<!-- The slideshow/carousel -->
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="/image/carousel/benz.png" class="d-block" style="width: 100%">
		</div>
		<div class="carousel-item">
			<img src="/image/carousel/bmw.png" class="d-block" style="width: 100%">
		</div>
		<div class="carousel-item">
			<img src="/image/carousel/audi.png" class="d-block" style="width: 100%">
		</div>
	</div>

</div>
<!-- Carousel end -->

<!-- Main -->
<!-- 
<div class="container mt-3">
	
	<c:if test="${!empty postList }">
		<div class="card">
			<c:forEach var="post" items="${ postList.content }">
				<div class="card-body">
					<h4 class="card-title">${ post.title }</h4>
					<a href="/post/${ post.id }" class="btn btn-secondary">상세보기</a>
				</div>
			</c:forEach>
		</div>

		<br>
		<ul class="pagination justify-content-center">
			<li class="page-item <c:if test="${ postList.first }">disabled</c:if>"><a class="page-link" href="?page=${ postList.number - 1 }">이전</a></li>

			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item active"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>

			<li class="page-item <c:if test="${ postList.last }">disabled</c:if>"><a class="page-link" href="?page=${ postList.number + 1 }">다음</a></li>
		</ul>
	</c:if>

</div>
	-->


<!-- Contact Us -->
<div style="background-image: url('/image/main/contactus.png'); background-size: cover; background-repeat: no-repeat;">
	<div class="container">
		<br> <br>
		<div class="row" style="background-color: rgba(251, 206, 177, 0.3);">
			<div class="row p-5" style="text-align: center;">
				<div class="col-2"></div>
				<div class="col-8">
					<h2 style="font-weight: bold; color: #333333">문의하기</h2>
				</div>
				<div class="col-2"></div>
				<div class="col-2 mt-2"></div>
				<div class="col-8 mt-2">
					<h3 style="font-weight: bold; color: #333333">
						<i class="bi bi-telephone-fill"></i>1599-5866
					</h3>
				</div>
				<div class="col-2 mt-2"></div>
				<div class="col-sm-12 col-md-6  mt-4">
					<input class="form-control" type="text" style="border-style: none; border-radius: 0; height: 50px;" placeholder="고객성함">
				</div>
				<div class="col-sm-12 col-md-6 mt-4">
					<input class="form-control" type="text" style="border-style: none; border-radius: 0; height: 50px;" placeholder="전화번호">
				</div>
				<div class="col-sm-12 col-md-6 mt-2">
					<input class="form-control" type="text" style="border-style: none; border-radius: 0; height: 50px;" placeholder="희망일시">
				</div>
				<div class="col-sm-12 col-md-6 mt-2">
					<input class="form-control" type="text" style="border-style: none; border-radius: 0; height: 50px;" placeholder="탁송차종">
				</div>
				<div class="col-sm-12 col-md-6 mt-2">
					<input class="form-control" type="text" style="border-style: none; border-radius: 0; height: 50px;" placeholder="출발지역">
				</div>
				<div class="col-sm-12 col-md-6 mt-2">
					<input class="form-control" type="text" style="border-style: none; border-radius: 0; height: 50px;" placeholder="도착지역">
				</div>
				<div class="col-sm-12 mt-2">
					<textarea class="form-control" rows="" cols="" readonly="readonly" style="background-color: #ffffff; min-height: 80px; resize: none; border-style: none; border-radius: 0; color: #666666">
더카탁송은 (이하 '회사') 고객님의 개인정보를 중요시하며,
"정보통신망 이용촉진 및 정보보호"에 관한 법률을 준수하고 있습니다.
회사는 개인정보취급방침을 통하여 고객님께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며, 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다.
[수집하는 개인정보의 항목 및 수집방법]
회사는 회원가입, 상담을 위해 아래와 같은 방법에 따라 개인정보를 수집하고 있습니다.
-회원가입 시
ο 필수항목 : 아이디, 비밀번호, 성명,K 생년월일, 회사/기관명, e-mail, 휴대전화, 회사전화
ο 선택항목 : 부서/직위, 팩스번호
-교육, 인증, 도서구매, Q&amp;A, 이용 시
ο 필수항목 : 아이디, 비밀번호, 성명,K 생년월일, 회사/기관명, e-mail, 휴대전화, 회사전화
ο 선택항목 : 없음

[개인정보의 수집 및 이용목적]
회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다.
ο 회원 관리
회원제 서비스 이용에 따른 본인확인 , 개인 식별 , 불량회원의 부정 이용 방지와 비인가 사용 방지 , 가입 의사 확인 , 불만처리 등 민원처리 , 고지사항 전달
ο 게시판 관리
질문게시판 및 회원게시판 이용에 따른 본인확인, 불량회원의 스팸, 광고성 게시글 차단

[개인정보의 보유 및 이용기간]
원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 단, 상법 및 ‘전자상거래등에서 소비자보호에 관한 법률’ 등 관련 법령의 규정에 의하여 다음과 같이 거래 관련 관리 의무 관계의 확인 등을 이유로 일정기간 보유하여야 할 필요가 있을 경우에는 일정기간 보유합니다.
ο 계약 또는 청약철회 등에 관한 기록 : 5년 (전자상거래등에서의 소비자보호에 관한 법률)
ο 대금결제 및 재화 등의 공급에 관한 기록 : 5년 (전자상거래등에서의 소비자보호에 관한 법률)
ο 소비자의 불만 또는 분쟁처리에 관한 기록 : 3년 (전자상거래등에서의 소비자보호에 관한 법률)
ο 설문조사, 이벤트 등 일시적 목적을 위하여 수집한 경우 : 당해 설문조사, 이벤트 등의 종료 시점
[개인정보 파기절차 및 방법]
이용자의 개인정보는 원칙적으로 개인정보의 수집 및 이용목적이 달성되면 지체 없이 파기합니다.
회사의 개인정보 파기절차 및 방법은 다음과 같습니다.
ο 파기절차
이용자가 서비스 이용 등을 위해 입력하신 정보는 목적이 달성된 후 별도의 DB로 옮겨져(종이의 경우 별도의 서류함) 내부 방침 및 기타 관련 법령에 의한 정보보호 사유에 따라(보유 및 이용기간 참조) 일정 기간 저장된 후 파기되어집니다.
별도 DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 보유되어지는 이외의 다른 목적으로 이용되지 않습니다.
ο 파기방법
- 종이에 출력된 개인정보 : 분쇄기로 분쇄하거나 소각
- 전자적 파일 형태로 저장된 개인정보 : 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제
[개인정보의 제3자 제공]
이용자의 개인정보는 원칙적으로 외부에 제공하지 않습니다. 다만, 아래의 경우에는 예외로 합니다.
ο 이용자들이 사전에 동의한 경우
- 정보수집 또는 정보제공 이전에 회원님께 비즈니스 파트너가 누구인지, 어떤 정보가 왜 필요한지, 그리고 언제까지 어떻게 보호/관리되는지 알려드리고 동의를 구하는 절차를 거치게 되며, 회원님께서 동의하지 않는 경우에는 추가적인 정보를 수집하거나 비즈니스 파트너와 공유하지 않습니다.
ο 법령의 규정에 의거하거나, 수사 목적으로 법령에 정해진 절차와 방법에 따라 수사기관의 요구가 있는 경우
[이용자 및 법정대리인의 권리와 그 행사방법]
ο 이용자 및 법정 대리인은 언제든지 등록되어 있는 자신 혹은 당해 만 14세 미만 아동의 개인정보를 조회하거나 수정할 수 있으며 동의철회(가입해지)를 요청할 수도 있습니다.
ο 이용자 혹은 만 14세 미만 아동의 개인정보 조회.수정을 위해서는 로그인 후 회원공간에서 '개인정보변경'을 가입 해지(동의철회)를 위해서는 로그인 후 회원공간에서"회원탈퇴"를 클릭하여 본인 확인 절차를 거치신 후 직접 열람,정정 또는 탈퇴가 가능합니다. 혹은 개인정보보호책임자에게 서면, 전화 또는 이메일로 연락하시면 본인 확인 절차를 거친 후 지체 없이 조치하겠습니다.
ο 귀하가 개인정보의 오류에 대한 정정을 요청하신 경우에는 정정을 완료하기 전까지 당해 개인정보를 이용 또는 제공하지 않습니다. 또한 잘못된 개인정보를 제3자에게 이미 제공한 경우에는 정정 처리결과를 제3자에게 지체 없이 통지하여 정정이 이루어지도록 하겠습니다.
ο 회사는 이용자의 요청에 의해 해지 또는 삭제된 개인정보는 "회사가 수집하는 개인정보의 보유 및 이용기간"에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 이용할 수 없도록 처리하고 있습니다.
[개인정보 자동 수집 장치의 설치/운영 및 거부에 관한 사항]
회사는 이용자(접속자)의 정보를 수시로 저장하고 찾아내는 '쿠키(cookie)' 등을 운용합니다. 쿠키란 웹사이트를 운영하는데 이용되는 서버가 귀하의 브라우저에 보내는 아주 작은 텍스트 파일로서 귀하의 컴퓨터 하드디스크에 저장됩니다.
회사는 다음과 같은 목적을 위해 쿠키를 사용합니다.
ο 쿠키 등 사용 목적
- 회원과 비회원의 접속 빈도나 방문 시간 등을 분석, 팝업창 거부 설정
- 귀하는 쿠키 설치에 대한 선택권을 가지고 있습니다. 따라서, 귀하는 웹브라우저에서 옵션을 설정함으로써 모든 쿠키를 허용하거나, 쿠키가 저장될 때마다 확인을 거치거나, 아니면 모든 쿠키의 저장을 거부할 수도 있습니다.
ο 쿠키 설정 거부 방법
- 쿠키 설정을 거부하는 방법으로는 회원님이 사용하시는 웹 브라우저의 옵션을 선택함으로써 모든 쿠키를 허용하거나 쿠키를 저장할 때마다 확인을 거치거나, 모든 쿠키의 저장을 거부할 수 있습니다.
- 설정방법 예(인터넷 익스플로어의 경우) : 웹 브라우저 상단의 도구 > 인터넷 옵션 > 개인정보
- 단, 귀하께서 쿠키 설치를 거부하였을 경우 서비스 제공에 어려움이 있을 수 있습니다.

[개인정보관리책임자 및 담당자의 연락처]
회사는 고객의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 관련 부서 및 개인정보관리책임자를 지정하고 있습니다.

ο개인정보 관리담당자
- 소속부서 :
- 직위 : 개인정보 관리담당자
- 성명 : 김대현
- 전화번호 : 1599-5866
- 이메일 : wk8735@naver.com 

ο 귀하께서는 회사의 서비스를 이용하시며 발생하는 모든 개인정보보호 관련 민원을 개인정보관리담당자 혹은 담당부서로 신고하실 수 있습니다.
ο 회사는 이용자들의 신고사항에 대해 신속하게 충분한 답변을 드릴 것입니다.
ο 기타 개인정보침해에 대한 신고나 상담이 필요하신 경우에는 아래 기관에 문의하시기 바랍니다.
- 개인분쟁조정위원회 (www.1336.or.kr/1336)
- 정보보호마크인증위원회 (www.eprivacy.or.kr/02-580-0533~4)
- 대검찰청 인터넷범죄수사센터 (http://icic.sppo.go.kr/02-3480-3600)
- 경찰청 사이버테러대응센터 (www.ctrc.go.kr/02-392-0330)

현 개인정보취급방침은 2017년 06월 01일부터 적용됩니다.</textarea>
				</div>
				<div class="col-sm-12 mt-1 form-group" style="text-align: right;">
					<input type="checkbox"><span style="font-weight: bold;">개인정보처리방침에 동의합니다.</span>
				</div>
				<div class="row mt-4">
					<div class="col-sm-0 col-md-4"></div>
					<div class="col-sm-12 col-md-4">
						<button class="btn btn-lg" style="background-color: #333333; color: #fafafa">문의하기</button>
					</div>
					<div class="col-sm-0 col-md-4"></div>
				</div>
			</div>
		</div>
		<br> <br>
	</div>
</div>
<!-- Contact Us end -->


<!-- Main end -->


<%@ include file="./layout/footer.jsp"%>