let userObject = {

	init: function() {
		let _this = this;

		// 휴대전화 인증 요청 + 회원가입
		$("#btn-phoneCheck").on("click", () => {
			_this.phoneCheck();
		});

	},

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
				// 휴대전화 번호 수정 불가
				document.getElementById("phonenumber").disabled = true;
				// 휴대전화 인증확인 버튼 클릭 가능
				document.getElementById("btn-phonCheckOK").classList.remove("disabled");

				$("#btn-phonCheckOK").on("click", () => {
					let codeCheck = $("#phonenumberCheck").val();
					if (code === codeCheck) {
						let user2 = {
							phone: user1.phone
						}
						// 인증번호가 같을 경우
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

}

userObject.init();