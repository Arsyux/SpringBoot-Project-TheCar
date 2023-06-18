package com.arsyux.thecar.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arsyux.thecar.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
