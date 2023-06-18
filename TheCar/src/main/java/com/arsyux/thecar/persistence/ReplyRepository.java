package com.arsyux.thecar.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arsyux.thecar.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
