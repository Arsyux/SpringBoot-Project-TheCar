// userObject 선언
let userObject = {
	// init() 함수 선언
	init: function() {
		let _this = this;

		$("#btn-insertUser").on("click", () => {
			_this.insertUser();
		});
		
		$("#btn-update").on("click", () => {
			_this.updateUser();
		});
	},
	
	insertUser: function(){
		alert("회원가입 요청됨");
		// 사용자가 입력한 값 추출
		let user = {
			username : $("#username").val(),
			password : $("#password").val(),
		}
		alert(JSON.stringify(user));
		
		// Ajax를 이용한 비동기 호출
		$.ajax({
			type: "POST",
			url: "/auth/insertUser",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			if(status == 200) {
				let message = response["data"];
				alert(message);
				location = "/";	
			} else {
				let warn = "";
				let errors = response["data"];
				if(errors.username != null) { warn = warn + errors.username + "\n" }
				if(errors.password != null) { warn = warn + errors.password }
				alert(warn);
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
	},
	
	updateUser: function(){
		alert("회원 정보 수정 요청됨");
		// 사용자가 입력한 값 추출
		let user = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		}

		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/";	
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
	},
}

// userObject 객체의 init() 함수 호출
userObject.init();