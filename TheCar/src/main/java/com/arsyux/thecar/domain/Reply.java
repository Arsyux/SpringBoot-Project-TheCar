package com.arsyux.thecar.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {

	// pk
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	
	// 댓글 내용
	@Column(nullable = false, length = 200)
	private String content;
	
	// 댓글 등록일
	@CreationTimestamp
	private Timestamp createDate;
	
	// 포스트 상세 화면에서 댓글 정보를 출력하기 위해서는 댓글을 작성한 User정보와 댓글을 포함하는 Post 정보도 필요하다.
	// 따라서 이를 위한 연관매핑을 설정한다.
	// 우선, 여러 개의 댓글을 한 명의 회원이 등록할 수 있으므로 Reply와 User는 N:1 (다 대 일)관계다.
	// 따라서 @ManyToOne을 이용하여 연관 매핑을 처리한다. 마찬가지로 여러 개의 댓글을 하나의 포스트에 등록할 수 있기 때문에
	// Reply와 Post 역시 N:1 (다 대 일) 관계로 매핑한다.
	
	// 양방향 매핑
	// 포스트의 상세 화면에서는 해당 포스트에 속한 댓글 목록도 같이 출력되어야 한다.
	// 즉, Post를 통해 Reply 정보도 조회할 수 있어야 한다. 이를 위해 Post와 Reply를 1:N (일 대 다) 관계로
	// 한번 더 매핑한다. 따라서 Reply와 Post는 N:1 (다 대 일)과 1:N(일 대 다) 관계로 양방향 매핑을 해야한다. 
	
	// 데이터베이스에서는 테이블의 양방향 매핑을 하나의 외래키만 사용하여 처리한다.
	// 반면, 객체를 양방향으로 매핑하기 위해서는 관계에 참여하는 두 객체가 서로에 대한 참조변수를 가지고 있어야 한다.
	// 이때, 두 참조 변수 중 하나를 선택하여 연관관계의 주인으로 설정해야 하는데 일반적으로 외래키 필드를 가진 엔티티를
	// 연관관계의 주인으로 지정한다.
	// 그리고 반대쪽 객체에 연관관계의 주인이 아님을 명시하기 위해 mappedBy 속성을 설정한다.
	
	// 연관된 사용자
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User user;
	
	// 연관된 포스트
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postid")
	private Post post;
	
}
