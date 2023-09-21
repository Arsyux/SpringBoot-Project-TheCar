package com.arsyux.thecar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.thecar.domain.Reply;
import com.arsyux.thecar.dto.ResponseDTO;
import com.arsyux.thecar.security.UserDetailsImpl;
import com.arsyux.thecar.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	// Reply 엔티티 생성에 필요한 정보들을 인자로 전달하면서 ReplyService 클래스의 insertReply() 메소드를 호출한다.
	@PostMapping("/reply/{postId}")
	public @ResponseBody ResponseDTO<?> insertReply(@PathVariable int postId, @RequestBody Reply reply,
			@AuthenticationPrincipal UserDetailsImpl principal) {
	//public @ResponseBody ResponseDTO<?> insertReply(@PathVariable int postId, @RequestBody Reply reply, HttpSession session) {
		
		//User principal = (User) session.getAttribute("principal");
		//replyService.insertReply(postId, reply, principal);
		replyService.insertReply(postId, reply, principal.getUser());
		
		return new ResponseDTO<>(HttpStatus.OK.value(), postId + "번 포스트에 대한 댓글이 등록되었습니다.");
	}
	
	@DeleteMapping("/reply/{replyId}")
	public @ResponseBody ResponseDTO<?> deleteReply(@PathVariable int replyId) {
		replyService.deleteReply(replyId);
		return new ResponseDTO<>(HttpStatus.OK.value(), replyId +"번 댓글이 삭제되었습니다.");
	}
	
}
