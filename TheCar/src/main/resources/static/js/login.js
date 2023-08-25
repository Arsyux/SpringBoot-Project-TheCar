// loginObject 선언
let loginObject = {
	
	// init() 함수 선언
	init: function() {
		$("#btn-login").on("click", () => {
			this.login();
		})
	},
	
	login: function(){
		alert("로그인 요청됨");	
		let data = {
			username : $("#username").val(),
			password : $("#password").val()
		}
		$.ajax({
			type: "POST",
			url: "/auth/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8"
		}).done(function(response){
			let message = response["data"];
			alert(message);
			location = "/";
		}).fail(function(error){
			let message = error["data"];
			alert("문제 발생: " + message);
		});
	},
	
}

// loginObject 객체의 init() 함수 호출
loginObject.init();