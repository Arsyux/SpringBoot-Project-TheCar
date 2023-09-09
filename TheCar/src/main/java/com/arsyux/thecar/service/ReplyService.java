package com.arsyux.thecar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.domain.Reply;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.persistence.PostRepository;
import com.arsyux.thecar.persistence.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	// Post id를 사용하여 해당하는 Post를 찾고,
	// 댓글을 쓴 유저정보를 입력해준다. 
	@Transactional
	public void insertReply(int postId, Reply reply, User user) {
		Post post = postRepository.findById(postId).get();
		reply.setUser(user);
		reply.setPost(post);
		replyRepository.save(reply);
	}
	
}
