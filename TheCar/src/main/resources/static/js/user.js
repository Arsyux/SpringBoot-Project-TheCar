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
		
		// 아이디 찾기
		$("#btn-findUsername").on("click",  () => {
			_this.findUsername();
		});
		
		// 비밀번호 찾기
		$("#btn-findPassword").on("click", () => {
			_this.findPassword();
		});
		
		// 회원 정보 수정
		$("#btn-update").on("click", () => {
			_this.updateUser();
		});
		
		// 회원 탈퇴
		$("#btn-delete").on("click", () => {
			_this.deleteUser();
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
								document.getElementById(arrId[i]).classList.add('is-invalid');
								document.getElementById(arrId[i] + 'Invalid').innerText = jsonValue[arrId[i]];
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
	
	// 아이디 찾기
	findUsername: function(){
		
		let user = {
			name : $("#name").val(),
			phone : $("#phone").val(),
			email : $("#email").val()
		}
		
		$.ajax({
			type: "POST",
			url: "/auth/findUsername",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			if(status == 200) {
				// 아이디를 찾았을 경우
				let arrId = [ 'name', 'phone', 'email'];
				for(let i=0; i<arrId.length; i++) {
					if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
						document.getElementById(arrId[i]).classList.remove('is-valid');
					}
					if(document.getElementById(arrId[i]).classList.contains('is-invalid')) {
						document.getElementById(arrId[i]).classList.remove('is-invalid');
					}
					document.getElementById(arrId[i] + 'Invalid').innerText = '';
				}

				let	findUsername = response["data"];
				
				document.getElementById('findUsernameResultArea').hidden = false;
				document.getElementById('findUsernameResult').innerText = "회원님의 아이디는 [ " + findUsername + " ] 입니다.\n*표가 없는 아이디를 확인하시려면 전화로 문의해주시기 바랍니다.";	
			} else {
				// 유효성검사 통과x
				let errors = response["data"];
				
				try{
					// errors가 JSON인지 체크
					let jsonValue = JSON.parse(JSON.stringify(errors));
					let jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						// 유효성 검사 표시
						let arrId = [ 'name', 'phone', 'email'];
						for(let i=0; i<arrId.length; i++) {
							if(jsonValue[arrId[i]] != null) {
								if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
									document.getElementById(arrId[i]).classList.remove('is-valid');
								}
								document.getElementById(arrId[i]).classList.add('is-invalid');
								document.getElementById(arrId[i] + 'Invalid').innerText = jsonValue[arrId[i]];
							} else {
								if(document.getElementById(arrId[i]).classList.contains('is-invalid')){
									document.getElementById(arrId[i]).classList.remove('is-invalid');
								}
								document.getElementById(arrId[i]).classList.add('is-valid');
								document.getElementById(arrId[i] + 'Invalid').innerText = '';
							}
						}
					} else {
						// 유효성 검사는 통과했으나 찾는 아이디가 없는 경우
						let arrId = [ 'name', 'phone', 'email'];
						for(let i=0; i<arrId.length; i++) {
							if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
								document.getElementById(arrId[i]).classList.remove('is-valid');
							}
							if(document.getElementById(arrId[i]).classList.contains('is-invalid')) {
								document.getElementById(arrId[i]).classList.remove('is-invalid');
							}
							document.getElementById(arrId[i] + 'Invalid').innerText = '';
						}
						
						document.getElementById('findUsernameResultArea').hidden = false;
						document.getElementById('findUsernameResult').innerText = errors;
					}
				} catch (e) {
					alert("에러");
				}
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
	},
	
	// 비밀번호 찾기
	findPassword: function(){
		
		let user = {
			username : $("#username").val(),
			name : $("#name").val(),
			phone : $("#phone").val(),
			email : $("#email").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/auth/findPassword",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			if(status == 200) {
				// 비밀번호를 찾았을 경우
				let arrId = [ 'username', 'name', 'phone', 'email' ];
				for(let i=0; i<arrId.length; i++) {
					if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
						document.getElementById(arrId[i]).classList.remove('is-valid');
					}
					if(document.getElementById(arrId[i]).classList.contains('is-invalid')) {
						document.getElementById(arrId[i]).classList.remove('is-invalid');
					}
					document.getElementById(arrId[i] + 'Invalid').innerText = '';
				}

				document.getElementById('findPasswordResultArea').hidden = false;
				
				$("#btn-changePassword").on("click", () => {
					
					user = {
						userid : parseInt(response["data"]),
						username : user.username,
						password : $("#password").val(),
						name : user.name,
						phone : user.phone,
						email : user.email
					}
					$.ajax({
						type: "POST",
						url: "/auth/changePassword",
						data: JSON.stringify(user),
						contentType: "application/json; charset=utf-8"
					}).done(function(response) {
						status = response["status"];
						if(status == 200) {
							// 비밀번호 변경 완료
							alert('비밀번호가 변경되었습니다.');
							location = '/auth/loginUser';
						} else {
							// 유효성 검사 통과x
							let errors = response["data"];
							
							try{
								// errors가 JSON인지 체크
								let jsonValue = JSON.parse(JSON.stringify(errors));
								let jsonCheck = typeof jsonValue === 'object';
								
								if (jsonCheck) {
									// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
									// 유효성 검사 표시
									let arrId = [ 'password' ];
									for(let i=0; i<arrId.length; i++) {
										if(jsonValue[arrId[i]] != null) {
											if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
												document.getElementById(arrId[i]).classList.remove('is-valid');
											}
											document.getElementById(arrId[i]).classList.add('is-invalid');
											document.getElementById(arrId[i] + 'Invalid').innerText = jsonValue[arrId[i]];
										} else {
											if(document.getElementById(arrId[i]).classList.contains('is-invalid')){
												document.getElementById(arrId[i]).classList.remove('is-invalid');
											}
											document.getElementById(arrId[i]).classList.add('is-valid');
											document.getElementById(arrId[i] + 'Invalid').innerText = '';
										}
									}
								} else {
									// 유효성 검사는 통과했으나 찾는 아이디가 없는 경우
									let arrId = [ 'username', 'name', 'phone', 'email'];
									for(let i=0; i<arrId.length; i++) {
										if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
											document.getElementById(arrId[i]).classList.remove('is-valid');
										}
										if(document.getElementById(arrId[i]).classList.contains('is-invalid')) {
											document.getElementById(arrId[i]).classList.remove('is-invalid');
										}
										document.getElementById(arrId[i] + 'Invalid').innerText = '';
									}
									
									document.getElementById('findUsernameResultArea').hidden = false;
									document.getElementById('findUsernameResult').innerText = errors;
								}
							} catch (e) {
								alert("해당하는 유저를 찾지 못하였습니다.");
							}
						}
					}).fail(function(error) {
						alert("에러 발생 : " + error);
					});
				});
					
			} else {
				// 유효성검사 통과x
				let errors = response["data"];
				
				try{
					// errors가 JSON인지 체크
					let jsonValue = JSON.parse(JSON.stringify(errors));
					let jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						// 유효성 검사 표시
						let arrId = [ 'username', 'name', 'phone', 'email'];
						for(let i=0; i<arrId.length; i++) {
							if(jsonValue[arrId[i]] != null) {
								if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
									document.getElementById(arrId[i]).classList.remove('is-valid');
								}
								document.getElementById(arrId[i]).classList.add('is-invalid');
								document.getElementById(arrId[i] + 'Invalid').innerText = jsonValue[arrId[i]];
							} else {
								if(document.getElementById(arrId[i]).classList.contains('is-invalid')){
									document.getElementById(arrId[i]).classList.remove('is-invalid');
								}
								document.getElementById(arrId[i]).classList.add('is-valid');
								document.getElementById(arrId[i] + 'Invalid').innerText = '';
							}
						}
					} else {
						// 유효성 검사는 통과했으나 찾는 아이디가 없는 경우
						let arrId = [ 'name', 'phone', 'email'];
						for(let i=0; i<arrId.length; i++) {
							if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
								document.getElementById(arrId[i]).classList.remove('is-valid');
							}
							if(document.getElementById(arrId[i]).classList.contains('is-invalid')) {
								document.getElementById(arrId[i]).classList.remove('is-invalid');
							}
							document.getElementById(arrId[i] + 'Invalid').innerText = '';
						}
						
						document.getElementById('findUsernameResultArea').hidden = false;
						document.getElementById('findUsernameResult').innerText = errors;
					}
				} catch (e) {
					alert("해당하는 유저를 찾지 못하였습니다.");
				}
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
	},
	
	// 회원 정보 수정
	updateUser: function(){
		
		let user = {
			password: $("#password").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/user/updateUser",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			let message = response["data"];
			if(status == 200) {
				// 비밀번호를 찾았을 경우
				let arrId = [ 'password' ];
				for(let i=0; i<arrId.length; i++) {
					if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
						document.getElementById(arrId[i]).classList.remove('is-valid');
					}
					if(document.getElementById(arrId[i]).classList.contains('is-invalid')) {
						document.getElementById(arrId[i]).classList.remove('is-invalid');
					}
					document.getElementById(arrId[i] + 'Invalid').innerText = '';
				}
				
				alert(message);
				
				location = "/auth/logout";
			} else {
				// 유효성검사 통과x
				let errors = response["data"];
				
				try{
					// errors가 JSON인지 체크
					let jsonValue = JSON.parse(JSON.stringify(errors));
					let jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						// 유효성 검사 표시
						let arrId = [ 'password'];
						for(let i=0; i<arrId.length; i++) {
							if(jsonValue[arrId[i]] != null) {
								if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
									document.getElementById(arrId[i]).classList.remove('is-valid');
								}
								document.getElementById(arrId[i]).classList.add('is-invalid');
								document.getElementById(arrId[i] + 'Invalid').innerText = jsonValue[arrId[i]];
							} else {
								if(document.getElementById(arrId[i]).classList.contains('is-invalid')){
									document.getElementById(arrId[i]).classList.remove('is-invalid');
								}
								document.getElementById(arrId[i]).classList.add('is-valid');
								document.getElementById(arrId[i] + 'Invalid').innerText = '';
							}
						}
					} else {
						// 유효성 검사는 통과했으나 찾는 아이디가 없는 경우
						let arrId = [ 'password'];
						for(let i=0; i<arrId.length; i++) {
							if(document.getElementById(arrId[i]).classList.contains('is-valid')) {
								document.getElementById(arrId[i]).classList.remove('is-valid');
							}
							if(document.getElementById(arrId[i]).classList.contains('is-invalid')) {
								document.getElementById(arrId[i]).classList.remove('is-invalid');
							}
							document.getElementById(arrId[i] + 'Invalid').innerText = '';
						}
						alert(errors);
					}
				} catch (e) {
					alert("해당하는 유저를 찾지 못하였습니다.");
				}
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
	},
	
	// 회원 탈퇴
	deleteUser: function(){
		
		if(!confirm("정말 탈퇴하시겠습니까?")) { return; }
		
		$.ajax({
			type: "DELETE",
			url: "/user/deleteUser",
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			let message = response["data"];
			if(status == 200) {
				
				alert(message);
				
				location = "/auth/logout";
			} else {
				let errors = response["data"];
				
				alert(errors);
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
	},
}

userObject.init();