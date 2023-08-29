package com.arsyux.thecar.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.Post;

// Post엔티티를 작성 후 이를 이용하여 CRUD 기능을 처리할 PostRepository 인터페이스를 작성.
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

		
}
