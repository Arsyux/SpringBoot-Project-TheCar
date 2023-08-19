// 해당 프로젝트의 모든 기능을 제이쿼리 기반으로 구현
// user.js 파일이 로딩되는 순간 userObject 객체의 init() 함수가 자동으로 호출된다.
// 그리고 회원가입 화면(insertUser.jsp)에서 회원 가입 버튼이 클릭되는 순간, init() 함수가
// 바로 아래에 선언된 insertUser() 함수를 호출한다.

// userObject 선언
let userObject = {
	// init() 함수 선언
	init: function() {
		let _this = this;

		// #btn-save 버튼에 click 이벤트가 발생하면 insertUser() 함수 호출
		// id 선택자인 #id를 통해 찾는다. class의 경우 .class를 사용
		$("#btn-save").on("click", () => {
			_this.insertUser();
		})
	},
	
	insertUser: function(){
		alert("회원가입 요청됨");
		// 사용자가 입력한 값 추출
		let user = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		}
		// user객체의 값을 콘솔에 출력
		//console.log(user);
		
		// Ajax를 이용한 비동기 호출
		// done(): 요청 처리에 성공했을 때 실행될 코드
		// fail(): 요청 처리에 실패했을 때 실행될 코드
		// 회원 등록을 요청할 때는 Ajax(Asynchronous JavaScript and XML)를 이용하여 비동기 방식으로 처리한다.
		$.ajax({
			type: "POST", // 요청 방식
			url: "/auth/insertUser", // 요청 경로
			data: JSON.stringify(user), // user 객체를 JSON 형식으로 변환
			// HTTP의 body에 설정되는 데이터 마임타입
			contentType: "application/json; charset=utf-8"
			// 응답으로 들어온 JSON 데이터를 response로 받는다.
		}).done(function(response) {
			// 응답 메시지를 콘솔에 출력하고 메인 페이지로 이동
			console.log(response);
			location = "/";
			// 에러 발생 시 error로 에러 정보를 받는다.
		}).fail(function(error) {
			// 에러 메시지를 알림창에 출력
			alert("에러 발생 : " + error);
		});
	},
}

// userObject 객체의 init() 함수 호출
userObject.init();