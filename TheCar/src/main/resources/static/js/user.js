let userObject = {

	init: function() {
		let _this = this;

		// 회원 정보 수정
		$("#btn-update").on("click", () => {
			_this.updateUser();
		});

		// 휴대전화 인증 요청
		$("#btn-phoneCheck").on("click", () => {
			_this.phoneCheck();
		});
	},

	insertUser: function() {
		// 사용자가 입력한 값(username, password, email) 추출
		let user = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log(JSON.stringify(user));

		$.ajax({
			type: "POST", // 요청 방식
			url: "/auth/insertUser", // 요청 경로
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
			// 응답으로 들어온 JSON데이터를 response로 받는다.
		}).done(function(response) {
			// 응답 메시지를 콘솔에 출력하고 메인 페이지로 이동
			//console.log(response);
			let status = response["status"];
			if (status == 200) {
				alert("가입 완료 : " + user.username);
				location = "/";
			} else {
				let warn = "";
				let errors = response["data"];
				if (errors.username != null) warn = warn + errors.username + "\n";
				if (errors.password != null) warn = warn + errors.password + "\n";
				if (errors.email != null) warn = warn + errors.email;
				alert(warn);
			}
		}).fail(function(error) {
			// 에러 발생 시 error로 에러 정보를 받는다.
			// 에러 메시지를 알림창에 출력
			alert("에러 발생 : " + error);
		});
	},

	updateUser: function() {
		alert("회원 정보 수정");
		let user = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8",
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/";
		}).fail(function(error) {
			let message = error["data"];
			alert("문제 발생 : " + message);
		});
	},

	phoneCheck: function() {
		// 휴대전화 인증
		let user1 = {
			phone: $("#phonenumber").val()
		}
		$.ajax({
			type: "POST",
			url: "/auth/phoneCheck",
			data: JSON.stringify(user1),
			contentType: "application/json; charset=utf-8",
		}).done(function(response) {
			// 인증번호 발송
			alert('인증번호가 발송되었습니다.');
			let code = response["data"];

			// 휴대전화 번호 수정 불가
			document.getElementById("phonenumber").classList.add("disabled");
			// 휴대전화 인증확인 버튼 클릭 가능
			document.getElementById("btn-save").classList.remove("disabled");

			$("#btn-save").on("click", () => {
				let codeCheck = $("#phonenumberCheck").val();
				if (code === codeCheck) {
					// 성별
					let g;
					if (document.getElementById('rdoMan').checked) {
						g = 'Man';
					} else {
						g = 'Woman';
					}
					let user2 = {
						username: $("#username").val(),
						password: $("#password").val(),
						realname: $("#realname").val(),
						birthdate: $("#birthdate").val(),
						gender: g,
						phone: user1.phone // 중간에 번호가 변경되어도 이전 값으로 전송해야함.
					}
					$.ajax({
						type: "POST", // 요청 방식
						url: "/auth/insertUser", // 요청 경로
						data: JSON.stringify(user2),
						contentType: "application/json; charset=utf-8"
					}).done(function(response) {
						let status = response["status"];
						alert(response);
						if (status == 200) {
							alert(response["data"]);
							location = "/";
						} else {
							let warn = "";
							let errors = response["data"];
							if (errors.username != null) warn = warn + errors.username + "\n";
							if (errors.password != null) warn = warn + errors.password + "\n";
							alert(warn);
						}
					}).fail(function(error) {
						alert("인증 확인 요청에 실패하였습니다.");
					});

				} else {
					alert("인증번호가 올바르지 않습니다.");
				}
			});
		}).fail(function(error) {
			//let message = error["data"];
			alert('인증번호 발송에 실패하였습니다.');
		});
	},
}

userObject.init();