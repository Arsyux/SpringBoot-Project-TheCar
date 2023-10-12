// userObject 선언
let userObject = {
	// init() 함수 선언
	init: function() {
		let _this = this;
		
		// 회원가입
		$("#btn-insertUser").on("click", () => {
			_this.insertUser();
		});
		
		$("#btn-update").on("click", () => {
			_this.updateUser();
		});
	},
	
	// 회원가입
	insertUser: function(){
		// 성별 값
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
				
				// errors가 JSON인지 체크
				let jsonCheck = false;
				try{
					let jsonValue = JSON.parse(JSON.stringify(errors));
					jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						if(jsonValue['username'] != null) {
							alert('ㅎ');
						} else {
							alert('ㅗ');
						}
						alert(jsonValue["username"]);
					} else {
						alert("정상, jsonCheck false");
					}
				} catch (e) {
					jsonCheck = false;
					alert("에러, jsonCheck false");
				}			
	
				//let warn = "";
				
				
				//if(errors.username != null) { 
				//	warn = warn + errors.username + "\n"
				//}
				//if(errors.password != null) {
				//	warn = warn + errors.password
				//}
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