package com.arsyux.thecar.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// 댓글 내용
	@Column(nullable = false, length = 200)
	private String content;

	// 댓글 등록일
	@CreationTimestamp
	private Timestamp createDate;

	// 연관된 사용자
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User user;

	// 연관된 포스트
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postid")
	private Post post;
	
}
