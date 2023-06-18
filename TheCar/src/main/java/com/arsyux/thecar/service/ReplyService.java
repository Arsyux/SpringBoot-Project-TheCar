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

	@Transactional
	public void insertReply(int postid, Reply reply, User user) {
		Post post = postRepository.findById(postid).get();
		reply.setUser(user);
		reply.setPost(post);
		replyRepository.save(reply);
	}

	@Transactional
	public void deleteReply(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
}
