let replyObject = {

	init: function() {

		// 댓글 등록
		$("#btn-save-reply").on("click", () => {
			this.insertReply();
		});
		
	},
	
	insertReply: function(){
		alert("댓글 등록 요청됨");
		
		let id = $("#postId").val();
		
		let reply = {
			content : $("#reply-content").val(),
		}
		
		$.ajax({
			type: "POST",
			url: "/reply/" + id,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message)
			location = "/post/" + id;
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
	},
	
	// deleteReply()함수는 postId와 replyId를 매개변수로 가진다.
	// 1. replyId는 Reply 엔티티를 삭제하기 위해 필요한 정보다.
	// 2. postId는 댓글이 삭제된 후 포스트 상세 화면(getPost.jsp)으로 이동할 때
	// 상세 화면에서 사용할 Post엔티티 검색에 필요한 정보다.
	deleteReply: function(postId, replyId){
		alert("댓글 삭제 요청됨");
		
		$.ajax({
			type: "DELETE",
			url: "/reply/" + replyId,
		}).done(function(response) {
			let message = response["data"];
			alert(message)
			location = "/post/" + postId;
		});
	},
}

replyObject.init();