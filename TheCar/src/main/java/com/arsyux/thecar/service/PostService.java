package com.arsyux.thecar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.thecar.domain.Post;
import com.arsyux.thecar.persistence.PostDAO;

@Service
public class PostService {

	@Autowired
	private PostDAO postDAO;
	
	@Transactional
	public void insertPost(Post post) {
		postDAO.insertPost(post);
	}
	
	@Transactional(readOnly = true)
	public List<Post> getMainList() {
		return postDAO.getMainList();
	}
	
	// getPostList()의 기능은 등록된 포스트의 목록을 조회하는 것 뿐이므로 @Transactional(readOnly = true)으로 설정한다.
	/*
	@Transactional(readOnly = true)
	public List<Post> getPostList() {
		// 모든 포스트 목록을 조회하기 위해 PostRepository의 findAll() 메소드를 호출한다.
		return postRepository.findAll();
	}
	*/
	
	// 페이징 처리를 위해 Pageable 타입의 객체를 매개변수로 받는다.
	// 또한 반환타입을 List에서 페이지 정보를 가지고 있는 Page로 수정한다.
	//@Transactional(readOnly = true)
	//public Page<Post> getPostList(Pageable pageable) {
		//return postRepository.findAll(pageable);
	//}
	
	// JpaRepository 인터페이스의 findById() 메소드를 사용하여 Post 엔티티에 대한 상세 조회 기능을 처리한다.
	// 검색 기능인 getPost() 메소드도 getPostList()메소드와 마찬가지로 트랜잭션을 읽기전용(readOnly = true)으로 설정한다.
	@Transactional(readOnly = true)
	public Post getPost(int id) {
		//return postRepository.findById(id).get();
		return new Post();
	}
	
	// 검색한 Post 엔티티의 title과 content를 사용자가 입력한 값으로 변경하고,
	// 트랜잭션이 종료되면 JPA의 더티 체킹(dirty checking)에 의해 UPDATE 구문이 작성되어 처리된다.
	// 더티 체킹이란?
	// 트랜잭션이 종료되는 시점에 오염된 엔티티, 즉 값이 변경된 엔티티를 찾는 과정을 뜻한다.
	// JPA는 더티 체킹으로 변경된 엔티티를 찾고, 해당 엔티티에 대한 UPDATE구문을 생성하여 SQL 저장소에 등록한다.
	// 그리고 트랜잭션이 종료되는 시점에 해당 UPDATE 구문을 SQL 저장소에 저장된 다른 SQL구문과 함께 데이터베이스에 전송한다.
	// updatePost() 메소드에서 Post 엔티티를 수정한 후에 save() 메소드를 호출하지 않는 것은 updatePost() 메소드에 @Transactional을 설정했기 때문이다.
	@Transactional
	public void updatePost(Post post) {
		//Post findPost = postRepository.findById(post.getId()).get();
		//findPost.setTitle(post.getTitle());
		//findPost.setContent(post.getContent());
	}
	
	// 특정 Post 엔티티를 삭제하기 위해 deletePost() 메소드에서 JpaRepository 객체의 deleteById() 메소드를 사용한다.
	@Transactional
	public void deletePost(int id) {
		//postRepository.deleteById(id);
	}
	
}
