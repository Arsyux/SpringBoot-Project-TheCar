let userObject = {
	
	// init() 함수 선언
	init: function() {
		let _this = this;
		
		// 회원 정보 수정
		$("#btn-insertUser").on("click", () => {
			_this.insertUser();
		});
	},
	
	
	// 회원 정보 수정
	updateUser: function(){
		// 사용자가 입력한 값 추출
		let user = {
			password : $("#password").val(),
			email : $("#email").val()
		}

		$.ajax({
			type: "PUT",
			url: "/user/updateUser",
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
				
				// errors가 JSON인지 체크
				// jsonCheck = false;
				try{
					let jsonValue = JSON.parse(JSON.stringify(errors));
					let jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						// 로그인 아이디 유효성 검사
						if(jsonValue['username'] != null) {
							if(document.getElementById('username').classList.contains('is-valid')){
								document.getElementById('username').classList.remove('is-valid');
							}
							document.getElementById('username').classList.add('is-invalid');
							document.getElementById('usernameInvalid').innerText = jsonValue['username'];
						} else {
							if(document.getElementById('username').classList.contains('is-invalid')){
								document.getElementById('username').classList.remove('is-invalid');
							}
							document.getElementById('username').classList.add('is-valid');
							document.getElementById('usernameInvalid').innerText = '';
						}
						// 비밀번호 유효성 검사
						if(jsonValue['password'] != null) {
							if(document.getElementById('password').classList.contains('is-valid')){
								document.getElementById('password').classList.remove('is-valid');
							}
							document.getElementById('password').classList.add('is-invalid');
							document.getElementById('passwordInvalid').innerText = jsonValue['password'];
						} else {
							if(document.getElementById('password').classList.contains('is-invalid')){
								document.getElementById('password').classList.remove('is-invalid');
							}
							document.getElementById('password').classList.add('is-valid');
							document.getElementById('passwordInvalid').innerText = '';
						}
						// 이름 유효성 검사
						if(jsonValue['name'] != null) {
							if(document.getElementById('name').classList.contains('is-valid')){
								document.getElementById('name').classList.remove('is-valid');
							}
							document.getElementById('name').classList.add('is-invalid');
							document.getElementById('nameInvalid').innerText = jsonValue['name'];
						} else {
							if(document.getElementById('name').classList.contains('is-invalid')){
								document.getElementById('name').classList.remove('is-invalid');
							}
							document.getElementById('name').classList.add('is-valid');
							document.getElementById('nameInvalid').innerText = '';
						}
						// 생년월일 유효성 검사
						if(jsonValue['birth'] != null) {
							if(document.getElementById('birth').classList.contains('is-valid')){
								document.getElementById('birth').classList.remove('is-valid');
							}
							document.getElementById('birth').classList.add('is-invalid');
							document.getElementById('birthInvalid').innerText = jsonValue['birth'];
						} else {
							if(document.getElementById('birth').classList.contains('is-invalid')){
								document.getElementById('birth').classList.remove('is-invalid');
							}
							document.getElementById('birth').classList.add('is-valid');
							document.getElementById('birthInvalid').innerText = '';
						}
						// 휴대폰 유효성 검사
						if(jsonValue['phone'] != null) {
							if(document.getElementById('phone').classList.contains('is-valid')){
								document.getElementById('phone').classList.remove('is-valid');
							}
							document.getElementById('phone').classList.add('is-invalid');
							document.getElementById('phoneInvalid').innerText = jsonValue['phone'];
						} else {
							if(document.getElementById('phone').classList.contains('is-invalid')){
								document.getElementById('phone').classList.remove('is-invalid');
							}
							document.getElementById('phone').classList.add('is-valid');
							document.getElementById('phoneInvalid').innerText = '';
						}
						// 이메일 유효성 검사
						if(jsonValue['email'] != null) {
							if(document.getElementById('email').classList.contains('is-valid')){
								document.getElementById('email').classList.remove('is-valid');
							}
							document.getElementById('email').classList.add('is-invalid');
							document.getElementById('emailInvalid').innerText = jsonValue['email'];
						} else {
							if(document.getElementById('email').classList.contains('is-invalid')){
								document.getElementById('email').classList.remove('is-invalid');
							}
							document.getElementById('email').classList.add('is-valid');
							document.getElementById('emailInvalid').innerText = '';
						}
					} else {
						alert(errors);
						
						// 작업중
						// 로그인 아이디 유효성 검사
						if(errors) {
							if(document.getElementById('username').classList.contains('is-valid')){
								document.getElementById('username').classList.remove('is-valid');
							}
							document.getElementById('username').classList.add('is-invalid');
							document.getElementById('usernameInvalid').innerText = jsonValue['username'];
						} else {
							if(document.getElementById('username').classList.contains('is-invalid')){
								document.getElementById('username').classList.remove('is-invalid');
							}
							document.getElementById('username').classList.add('is-valid');
							document.getElementById('usernameInvalid').innerText = '';
						}
						// 비밀번호 유효성 검사
						if(document.getElementById('username').classList.contains('is-invalid')){
							document.getElementById('username').classList.remove('is-invalid');
						}
						document.getElementById('username').classList.add('is-valid');
						document.getElementById('usernameInvalid').innerText = '';
						
						// 비밀번호 유효성 검사
						if(document.getElementById('password').classList.contains('is-invalid')){
							document.getElementById('password').classList.remove('is-invalid');
						}
						document.getElementById('password').classList.add('is-valid');
						document.getElementById('passwordInvalid').innerText = '';
						
						// 이름 유효성 검사
						if(document.getElementById('name').classList.contains('is-invalid')){
							document.getElementById('name').classList.remove('is-invalid');
						}
						document.getElementById('name').classList.add('is-valid');
						document.getElementById('nameInvalid').innerText = '';
						
						// 생년월일 유효성 검사
						if(document.getElementById('birth').classList.contains('is-invalid')){
							document.getElementById('birth').classList.remove('is-invalid');
						}
						document.getElementById('birth').classList.add('is-valid');
						document.getElementById('birthInvalid').innerText = '';
						
						// 휴대폰 유효성 검사
						if(jsonValue['phone'] != null) {
							if(document.getElementById('phone').classList.contains('is-valid')){
								document.getElementById('phone').classList.remove('is-valid');
							}
							document.getElementById('phone').classList.add('is-invalid');
							document.getElementById('phoneInvalid').innerText = jsonValue['phone'];
						} else {
							if(document.getElementById('phone').classList.contains('is-invalid')){
								document.getElementById('phone').classList.remove('is-invalid');
							}
							document.getElementById('phone').classList.add('is-valid');
							document.getElementById('phoneInvalid').innerText = '';
						}
						if(document.getElementById('email').classList.contains('is-invalid')){
							document.getElementById('email').classList.remove('is-invalid');
						}
						document.getElementById('email').classList.add('is-valid');
						document.getElementById('emailInvalid').innerText = '';
					}
				} catch (e) {
					//jsonCheck = false;
					alert("에러");
				}
			}
			location = "/";	
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
	},
}

// userObject 객체의 init() 함수 호출
userObject.init();