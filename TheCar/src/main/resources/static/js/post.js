let postObject = {

	init: function() {
		let _this = this;

		// 포스트 등록
		$("#btn-insert").on("click", () => {
			_this.insertPost();
		});
		
		// 포스트 수정
		$("#btn-update").on("click", () => {
			_this.updatePost();
		});
		
		// 포스트 삭제
		$("#btn-delete").on("click", () =>{
			_this.deletePost();
		});
	},
	
	insertPost: function(){
		let post = {
			title : $("#title").val(),
			departures_postcode: $("#departures_postcode").val(),
			departures_address: $("#departures_address").val(),
			departures_detailAddress: $("#departures_detailAddress").val(),
			departures_extraAddress: $("#departures_extraAddress").val(),
			arrivals_postcode: $("#arrivals_postcode").val(),
			arrivals_address: $("#arrivals_address").val(),
			arrivals_detailAddress: $("#arrivals_detailAddress").val(),
			arrivals_extraAddress: $("#arrivals_extraAddress").val(),
			content : $("#content").val(),
		}
		
		$.ajax({
			type: "POST",
			url: "/post/insertPost",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			//let message = response["data"];
			//alert(message)
			//location = "/";
			let status = response["status"];
			if(status == 200) {
				let message = response["data"];
				alert(message);
				location = "/";	
			} else {
				let warn = "";
				let errors = response["data"];
				if(errors.title != null) { warn = warn + errors.title + "\n" }
				if(errors.content != null) { warn = warn + errors.content }
				alert(warn);
			}
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
	},
	
	updatePost: function(){
		alert("포스트 수정 요청됨");
	
		let post = {
			id: $("#id").val(),
			title : $("#title").val(),
			content : $("#content").val(),
		}
		
		$.ajax({
			type: "PUT",
			url: "/post",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message)
			location = "/";
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
	},
	
	deletePost: function(){
		alert("포스트 삭제 요청됨");
		
		let id = $("#id").text();
		
		
		$.ajax({
			type: "DELETE",
			url: "/post/" + id,
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message)
			location = "/";
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
	},
	
}

postObject.init();