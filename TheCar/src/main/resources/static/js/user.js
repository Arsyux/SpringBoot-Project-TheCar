let userObject = {

	init: function() {
		let _this = this;

		// 휴대전화 인증 요청 + 회원가입
		$("#btn-phoneCheck").on("click", () => {
			_this.phoneCheck();
		});

		// 회원 정보 수정
		$("#btn-update").on("click", () => {
			_this.updateUser();
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
			let status = response["status"];
			if (status == 200) {
				// 인증번호 발송
				alert('인증번호가 발송되었습니다.');
				document.getElementById("phonenumber").classList.remove('is-invalid');
				$("#phonenumberInvalid").text('');
				let code = response["data"];
				// 휴대전화 번호 수정 불가
				document.getElementById("phonenumber").disabled = true;
				// 휴대전화 인증확인 버튼 클릭 가능
				document.getElementById("btn-save").classList.remove("disabled");

				$("#btn-save").on("click", () => {
					let codeCheck = $("#phonenumberCheck").val();
					if (code === codeCheck) {
						// 성별
						let g = '남자';
						if (document.getElementById('rdoWoman').checked) {
							g = '여자';
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
							if (status == 200) {
								alert(response["data"]);
								location = "/";
							} else {
								let errors = response["data"];
								// 검사 항목 초기화
								// username
								document.getElementById("username").classList.remove('is-invalid');
								$("#usernameInvalid").text('');
								// password
								document.getElementById("password").classList.remove('is-invalid');
								$("#passwordInvalid").text('');
								// 이름
								document.getElementById("realname").classList.remove('is-invalid');
								$("#realnameInvalid").text('');
								// 생년월일
								document.getElementById("birthdate").classList.remove('is-invalid');
								$("#birthdateInvalid").text('');
								// 휴대폰 번호
								document.getElementById("phonenumber").classList.remove('is-invalid');
								$("#phonenumberInvalid").text('');

								if (errors.username != null) {
									document.getElementById("username").classList.add('is-invalid');
									$("#usernameInvalid").text(errors.username);
								}
								if (errors.password != null) {
									document.getElementById("password").classList.add('is-invalid');
									$("#passwordInvalid").text(errors.password);
								}
								if (errors.realname != null) {
									document.getElementById("realname").classList.add('is-invalid');
									$("#realnameInvalid").text(errors.realname);
								}
								if (errors.birthdate != null) {
									document.getElementById("birthdate").classList.add('is-invalid');
									$("#birthdateInvalid").text(errors.birthdate);
								}
								if (errors.phone != null) {
									document.getElementById("phonenumber").classList.add('is-invalid');
									$("#phonenumberInvalid").text(errors.phone);
								}
							}
						}).fail(function(error) {
							alert("인증 확인 요청에 실패하였습니다.");
						});

					} else {
						alert("인증번호가 올바르지 않습니다.");
					}
				});
			} else {
				let errors = response["data"];

				document.getElementById("phonenumber").classList.remove('is-invalid');
				$("#phonenumberInvalid").text('');

				if (errors.phone != null) {
					document.getElementById("phonenumber").classList.add('is-invalid');
					$("#phonenumberInvalid").text(errors.phone);
				}
			}
		}).fail(function(error) {
			//let message = error["data"];
			alert('인증번호 발송에 실패하였습니다.');
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

}

userObject.init();