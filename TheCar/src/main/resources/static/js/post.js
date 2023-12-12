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
		$("#btn-delete").on("click", () => {
			_this.deletePost();
		});
		
	},
	
	insertPost: function() {
		
		let post = {
			title: $("#title").val(),
			cartype: $("#cartype").val(),
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
			let status = response["status"];
			if(status == 200) {
				let postid = response["data"];
				
				let form = document.createElement("form");
		
				let data = document.getElementsByClassName('files');
				
				let dataLength = data.length;
				
				let postidField = document.createElement("input");
				postidField.setAttribute("type", "hidden");
				postidField.setAttribute("name", "postid");
				postidField.setAttribute("value", postid);
				
				form.appendChild(postidField);
				
				// form에 데이터를 담고 청소
				for(i=0; i< dataLength; i++){ form.appendChild(data[0]); }		
				document.getElementById('file_list').innerHTML = '';
				
				// body에 부착
				document.body.appendChild(form);
				var formData = new FormData(form);
				$.ajax({
			        url : '/file/insertFiles',
			        type : 'POST',
			        data : formData,
			        contentType : false,
			        processData : false        
			    }).done(function(response){
					alert(response["data"]);
					location = "/";
			    }).faiil(function(error){
					let message = error["data"];
					alert("첨부파일 업로드 에러 발생 : " + message);
					let deletePost = {
						postid: postid
					}
					$.ajax({
						type: "DELETE",
						url: "/post/" + postid,
						data: JSON.stringify(deletePost),
						contentType: "application/json; charset=utf-8"
					});
				});
			} else {
				let warn = "";
				let errors = response["data"];
				if(errors.title != null) { warn = warn + errors.title + "\n" }
				if(errors.content != null) { warn = warn + errors.content + "\n" }
				if(errors.departures_postcode != null) { warn = warn + errors.departures_postcode + "\n" }
				if(errors.departures_address != null) { warn = warn + errors.departures_address + "\n" }
				if(errors.departures_detailAddress != null) { warn = warn + errors.departures_detailAddress + "\n" }
				if(errors.departures_extraAddress != null) { warn = warn + errors.departures_extraAddress + "\n" }
				if(errors.arrivals_postcode != null) { warn = warn + errors.arrivals_postcode + "\n" }
				if(errors.arrivals_address != null) { warn = warn + errors.arrivals_address + "\n" }
				if(errors.arrivals_detailAddress != null) { warn = warn + errors.arrivals_detailAddress + "\n" }
				if(errors.arrivals_extraAddress != null) { warn = warn + errors.arrivals_extraAddress }
				alert(warn);
			}
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
	},
	
	deletePost: function() {
		
		if(!confirm("삭제하시겠습니까?")) { return; }
		
		let postid = $("#postid").val();
		let deletePost = {
			postid: postid
		}
		$.ajax({
			type: "DELETE",
			url: "/file/deleteFiles",
			data: JSON.stringify(deletePost),
			contentType: "application/json; charset=utf-8"    
	    }).done(function(response){
			if(response["status"] == 200) {
				// 파일 삭제에 성공했을 경우 게시글 삭제
				$.ajax({
					type: "DELETE",
					url: "/post/" + postid,
					data: JSON.stringify(deletePost),
					contentType: "application/json; charset=utf-8"    
			    }).done(function(response){
					alert(response["data"]);
					if(response["status"] == 200) {
						location = "/";
					}			
			    }).faiil(function(error){
					let message = error["data"];
					alert("에러 발생 : " + message);
				});
			} else {
				// 파일 삭제에 실패했을 경우
				alert(response["data"]);
			}
	    }).faiil(function(error){
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
		
	},
		
	updatePost: function() {
		
		if(!confirm("기존의 첨부파일이 삭제되고 새로 등록됩니다.")) { return; }
		
		let postid = $("#postid").val();
		
		let deleteFilesByPostId = {
			postid: postid
		}
		$.ajax({
			type: "DELETE",
			url: "/file/deleteFiles",
			data: JSON.stringify(deleteFilesByPostId),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			
			if(response["status"] != 200) {
				alert("기존의 첨부 파일의 삭제에 실패하였습니다.");
				return;
			}
			
			// 기존의 파일 삭제
			let post = {
				postid: postid,
				title: $("#title").val(),
				cartype: $("#cartype").val(),
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
				type: "PUT",
				url: "/post/updatePost",
				data: JSON.stringify(post),
				contentType: "application/json; charset=utf-8"
			}).done(function(response) {
				let status = response["status"];
				if(status == 200) {
					
					let form = document.createElement("form");
			
					let data = document.getElementsByClassName('files');
					
					let dataLength = data.length;
					
					let postidField = document.createElement("input");
					postidField.setAttribute("type", "hidden");
					postidField.setAttribute("name", "postid");
					postidField.setAttribute("value", postid);
					
					form.appendChild(postidField);
					
					// form에 데이터를 담고 청소
					for(i=0; i< dataLength; i++){ form.appendChild(data[0]); }		
					document.getElementById('file_list').innerHTML = '';
					
					// body에 부착
					document.body.appendChild(form);
					var formData = new FormData(form);
					$.ajax({
				        url : '/file/insertFiles',
				        type : 'POST',
				        data : formData,
				        contentType : false,
				        processData : false        
				    }).done(function(response){
						alert(response["data"]);
						location = "/";
				    }).faiil(function(error){
						let message = error["data"];
						alert("첨부파일 업로드 에러 발생 : " + message);
						let deletePost = {
							postid: postid
						}
						$.ajax({
							type: "DELETE",
							url: "/post/" + postid,
							data: JSON.stringify(deletePost),
							contentType: "application/json; charset=utf-8"
						});
					});
				} else {
					let warn = "";
					let errors = response["data"];
					if(errors.title != null) { warn = warn + errors.title + "\n" }
					if(errors.content != null) { warn = warn + errors.content + "\n" }
					if(errors.departures_postcode != null) { warn = warn + errors.departures_postcode + "\n" }
					if(errors.departures_address != null) { warn = warn + errors.departures_address + "\n" }
					if(errors.departures_detailAddress != null) { warn = warn + errors.departures_detailAddress + "\n" }
					if(errors.departures_extraAddress != null) { warn = warn + errors.departures_extraAddress + "\n" }
					if(errors.arrivals_postcode != null) { warn = warn + errors.arrivals_postcode + "\n" }
					if(errors.arrivals_address != null) { warn = warn + errors.arrivals_address + "\n" }
					if(errors.arrivals_detailAddress != null) { warn = warn + errors.arrivals_detailAddress + "\n" }
					if(errors.arrivals_extraAddress != null) { warn = warn + errors.arrivals_extraAddress }
					alert(warn);
				}
			}).fail(function(error) {
				let message = error["data"];
				
				alert("에러 발생 : " + message);
			});
			
		}).fail(function(error) {
			// 기존 파일 삭제중 에러 발생
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
	},
	
}

postObject.init();