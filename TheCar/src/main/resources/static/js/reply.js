let replyObject = {

	init: function() {
		$("#btn-save-reply").on("click", () => {
			this.inserReply();
		});
	},

	inserReply: function() {

		let id = $("#postId").val();
		let reply = {
			content: $("#reply-content").val()
		}

		$.ajax({
			type: "POST",
			url: "/reply/" + id,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/post/" + id;
		}).fail(function(error) {
			let message = error["data"];
			alert("문제 발생 : " + message);
			location = "/post/" + id;
		});
	},

	deleteReply: function(postId, replyId) {
		$.ajax({
			type: "DELETE",
			url: "/reply/" + replyId
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/post/" + postId;
		});
	},
}

replyObject.init();