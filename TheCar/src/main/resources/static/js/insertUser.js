let userObject = {

	init: function() {
		let _this = this;
		
		// 회원 가입
		$("#btn-insertUser").on("click", () => {
			_this.insertUser();
		});
		
		// 아이디 중복 체크
		$("#username").on("focusout",  () => {
			_this.usernameCheck();
		});
		
		// 휴대폰 번호 체크
		$("#phone").on("focusout",  () => {
			_this.phoneCheck();
		});
		
		// 이메일 중복 체크
		$("#email").on("focusout",  () => {
			_this.emailCheck();
		});
	},
	
	// 아이디 중복 체크
	usernameCheck: function(){
		// null 또는 공백 체크
		if($("#username").val() == "" || $("#username").val() == null) { return; }
		
		let user = {
			username : $("#username").val(),
		}
		
		$.ajax({
			type: "POST",
			url: "/auth/usernameCheck",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			if(status == 200) {
				// 중복 체크 통과
				let message = response["data"];
				// 사용 여부 체크
				if(confirm(message)) { document.getElementById('username').setAttribute('disabled', 'true'); }
			} else {
				// 중복 체크 실패
				let errors = response["data"];
				
				try{
					let jsonValue = JSON.parse(JSON.stringify(errors));
					let jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						// 유효성에서 걸렸으므로 유효성 알람
						if(jsonValue['username'] != null) {
							alert(jsonValue['username']);
						}
					} else {
						// 아이디 중복 알람
						alert(errors);
					}
				} catch (e) {
					alert("에러");
				}
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
	},
	
	// 휴대폰 번호 중복 체크
	phoneCheck: function(){
		// null 또는 공백 체크
		if($("#phone").val() == "" || $("#phone").val() == null) { return; }
		
		let user = {
			phone : $("#phone").val(),
		}
		
		$.ajax({
			type: "POST",
			url: "/auth/phoneCheck",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			if(status == 200) {
				// 중복 체크 통과
				let message = response["data"];
				// 사용 여부 체크
				if(confirm(message)) { document.getElementById('phone').setAttribute('disabled', 'true'); }
			} else {
				// 중복 체크 실패
				let errors = response["data"];
				
				try{
					let jsonValue = JSON.parse(JSON.stringify(errors));
					let jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						// 유효성에서 걸렸으므로 유효성 알람
						if(jsonValue['phone'] != null) {
							alert(jsonValue['phone']);
						}
					} else {
						// 아이디 중복 알람
						alert(errors);
					}
				} catch (e) {
					alert("에러");
				}
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
	},
	
	// 이메일 중복 체크
	emailCheck: function(){
		// null 또는 공백 체크
		if($("#email").val() == "" || $("#email").val() == null) { return; }
		
		let user = {
			email : $("#email").val(),
		}
		
		$.ajax({
			type: "POST",
			url: "/auth/emailCheck",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			if(status == 200) {
				// 중복 체크 통과
				let message = response["data"];
				// 사용 여부 체크
				if(confirm(message)) { document.getElementById('email').setAttribute('disabled', 'true'); }
			} else {
				// 중복 체크 실패
				let errors = response["data"];
				
				try{
					let jsonValue = JSON.parse(JSON.stringify(errors));
					let jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						// 유효성에서 걸렸으므로 유효성 알람
						if(jsonValue['email'] != null) {
							alert(jsonValue['email']);
						}
					} else {
						// 아이디 중복 알람
						alert(errors);
					}
				} catch (e) {
					alert("에러");
				}
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
	},
	
	// 회원 가입
	insertUser: function(){
		// 성별 값 구하기
		let genderVal;
		if(document.getElementById("rdoMan").checked){
			genderVal = 'M';
		} else {
			genderVal = 'W';
		}
		
		// 사용자가 입력한 값 추출
		let user = {
			username : $("#username").val(),
			password : $("#password").val(),
			name : $("#name").val(),
			birth : $("#birth").val(),
			gender : genderVal,
			phone : $("#phone").val(),
			email : $("#email").val()
		}
		
		// Ajax를 이용한 비동기 호출
		$.ajax({
			type: "POST",
			url: "/auth/insertUser",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			if(status == 200) {
				// 회원가입 성공
				let message = response["data"];
				alert(message);
				location = "/";	
			} else {
				// 회원가입 실패
				let errors = response["data"];
				
				try{
					// errors가 JSON인지 체크
					let jsonValue = JSON.parse(JSON.stringify(errors));
					let jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						// 유효성 검사 표시
						let arrId = [ 'username', 'password', 'name', 'birth', 'phone', 'email'];
						for(let i=0; i<arrId.length; i++) {
							if(jsonValue[arrId[i]] != null) {
								if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
									document.getElementById(arrId[i]).classList.remove('is-valid');
								}
								document.getElementById(arrId[i]).classList.add('is-valid');
								document.getElementById(arrId[i] + 'Invalid').innerText = '';
							} else {
								if(document.getElementById(arrId[i]).classList.contains('is-invalid')){
									document.getElementById(arrId[i]).classList.remove('is-invalid');
								}
								document.getElementById(arrId[i]).classList.add('is-valid');
								document.getElementById(arrId[i] + 'Invalid').innerText = '';
							}
						}
					} else {
						// 유효성 검사는 통과했으나 중복 검사에서 걸린 경우
						alert(errors);
					}
				} catch (e) {
					alert("에러");
				}
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
	},
	
}

userObject.init();