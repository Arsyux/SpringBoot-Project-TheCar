let userObject = {

	init: function() {
		let _this = this;

		// 휴대전화 인증 요청 + 회원가입
		$("#btn-phoneCheck").on("click", () => {
			_this.phoneCheck();
		});

		$("#btn-phoneCheck2").on("click", () => {
			_this.phoneCheck2();
		});

	},

	// 아이디 찾기
	phoneCheck: function() {
		document.getElementById("phonenumber").classList.remove('is-invalid');
		$("#phonenumberInvalid").text('');

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

				let code = response["data"];
				// 휴대전화 인증확인 버튼 클릭 가능
				document.getElementById("btn-phonCheckOK").classList.remove("disabled");

				$("#btn-phonCheckOK").on("click", () => {
					let codeCheck = $("#phonenumberCheck").val();
					if (code === codeCheck) {
						// 인증번호가 같을 경우
						// 휴대전화 번호 수정 불가
						document.getElementById("phonenumber").disabled = true;
						let user2 = {
							phone: user1.phone
						}
						$.ajax({
							type: "POST",
							url: "/auth/findUsername",
							data: JSON.stringify(user2),
							contentType: "application/json; charset=utf-8",
						}).done(function(response) {
							let userdata = response["data"];
							if (response["status"] == 200) {
								document.getElementById("findUsername").style.display = 'block';
								document.getElementById("findUsername").innerText = userdata;
							} else {
								document.getElementById("findUsername").style.display = 'block';
								document.getElementById("findUsername").innerText = userdata;
							}
						}).fail(function(error) {
							//let message = error["data"];
							alert('인증번호 발송에 실패하였습니다.');
						});

					} else {
						alert("인증번호가 올바르지 않습니다.");
						// 휴대전화 번호 수정 가능
						document.getElementById("phonenumber").disabled = false;
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

	// 비밀번호 찾기
	phoneCheck2: function() {
		document.getElementById("phonenumber").classList.remove('is-invalid');
		$("#phonenumberInvalid").text('');

		// 휴대전화 인증
		let user1 = {
			username: $("#username").val(),
			phone: $("#phonenumber").val()
		}

		$.ajax({
			type: "POST",
			url: "/auth/phoneCheck2",
			data: JSON.stringify(user1),
			contentType: "application/json; charset=utf-8",
		}).done(function(response) {
			let status = response["status"];
			if (status == 200) {
				// 인증번호 발송
				alert('인증번호가 발송되었습니다.');

				let code = response["data"];

				// 휴대전화 인증확인 버튼 클릭 가능
				document.getElementById("btn-phonCheckOK").classList.remove("disabled");

				$("#btn-phonCheckOK").on("click", () => {
					let codeCheck = $("#phonenumberCheck").val();
					if (code === codeCheck) {
						// 인증번호가 같을 경우

						// 휴대전화 번호 수정 불가
						document.getElementById("username").disabled = true;
						// 휴대전화 번호 수정 불가
						document.getElementById("phonenumber").disabled = true;

						let user2 = {
							username: user1.username,
							phone: user1.phone
						}
						$.ajax({
							type: "POST",
							url: "/auth/findPassword",
							data: JSON.stringify(user2),
							contentType: "application/json; charset=utf-8",
						}).done(function(response) {
							let userdata = response["data"];
							if (response["status"] == 200) {
								// 인증에 성공했을 경우 비밀번호 변경창 display
								document.getElementById("changePassword").style.display = 'block';
								document.getElementById("changePasswordOK").style.display = 'block';

								$("#btn-changePasswordOK").on("click", () => {
									let user3 = {
										username: userdata.username,
										password: $("#password").val(), // 변경된 비밀번호
										realname: userdata.realname,
										birthdate: userdata.birthDate,
										gender: userdata.gender,
										phone: userdata.phone
									}
									$.ajax({
										type: "PUT",
										url: "/auth/updateUserPassword",
										data: JSON.stringify(user3),
										contentType: "application/json; charset=utf-8",
									}).done(function(response) {
										let status = response["status"];

										if (status == 200) {
											// 비밀번호 변경에 성공
											let data2 = response["data"];
											alert(data2);
											location = "/";
										} else {
											let errors = response["data"];

											document.getElementById("password").classList.remove('is-invalid');
											$("#passwordInvalid").text('');

											if (errors.password != null) {
												document.getElementById("password").classList.add('is-invalid');
												$("#passwordInvalid").text(errors.password);
											}
										}
									}).fail(function(error) {
										alert('비밀번호 변경 요청이 실패하였습니다.');
									});
								});
							} else {
								// 인증에 실패했을 경우
								// 회원아이디 수정 가능
								document.getElementById("username").disabled = false;
								// 휴대전화 번호 수정 가능
								document.getElementById("phonenumber").disabled = false;
								document.getElementById("findUsername").style.display = 'block';
								document.getElementById("findUsername").innerText = userdata;
							}
						}).fail(function(error) {
							//let message = error["data"];
							alert('인증번호 발송에 실패하였습니다.');
						});

					} else {
						alert("인증번호가 올바르지 않습니다.");
						// 회원아이디 수정 가능
						document.getElementById("username").disabled = false;
						// 휴대전화 번호 수정 가능
						document.getElementById("phonenumber").disabled = false;
					}
				});
			} else {
				let errors = response["data"];

				document.getElementById("username").classList.remove('is-invalid');
				$("#usernameInvalid").text('');

				document.getElementById("phonenumber").classList.remove('is-invalid');
				$("#phonenumberInvalid").text('');

				if (errors.username != null) {
					document.getElementById("username").classList.add('is-invalid');
					$("#usernameInvalid").text(errors.username);
				}

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
}

userObject.init();