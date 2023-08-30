package com.arsyux.thecar.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.persistence.PostRepository;

@Service
public class PostService {

	// PostService에서는 PostRepository 의존성을 주입하여 데이터베이스 연동을 처리한다.
	@Autowired
	private PostRepository postRepository;
	
	// PostRepository 객체의 save() 메소드를 이용하여 insertPost() 메소드를 구현
	@Transactional
	public void insertPost(Post post) {
		// POST 등록시 외래키인 USER 엔티티가 Post.user 변수에 할당되어야한다.
		post.setCnt(0);
		postRepository.save(post);
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostList() {
		// 모든 포스트 목록을 조회하기 위해 PostRepository의 findAll() 메소드를 호출한다.
		// getPostList()의 기능은 등록된 포스트의 목록을 조회하는 것 뿐이므로 @Transactional(readOnly = true)으로 설정한다.
		return postRepository.findAll();
	}
}
