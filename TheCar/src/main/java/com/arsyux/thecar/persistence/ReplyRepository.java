package com.arsyux.thecar.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.Reply;

// ReplyRepository 인터페이스는 JpaRepository 인터페이스를 상속한다.
// 그리고 제네릭 타입은 각각 Reply와 Integer로 지정했다.
@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
