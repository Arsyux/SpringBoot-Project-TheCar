let userObject = {

	init: function() {
		let _this = this;
		
		// 아이디 찾기
		$("#btn-findUsername").on("click",  () => {
			_this.findUsername();
		});
		
		// 비밀번호 찾기
		$("#btn-findPassword").on("click", () => {
			_this.findPassword();
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
			type: "POST",
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
	}
	
}

userObject.init();