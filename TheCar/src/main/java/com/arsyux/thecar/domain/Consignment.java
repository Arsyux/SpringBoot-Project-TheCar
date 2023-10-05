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
public class Consignment {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)	
		private int id;
		
		// 예약자명
		@Column(nullable = false, length = 100)
		private String name;
		
		// 예약자 이메일
		@Column(nullable = false, length = 100)
		private String email;
		
		// 예약 종류 - 로드 탁송, 세이프티 탁송, 카캐리어 탁송,
		// 박스카캐리어 탁송, 제주도 탁송, 제주도 카캐리어 탁송, 해외차량 탁송
		// 오토바이 탁송, 보세운송
		@Column(nullable = false)
		private String type;

		// 예약일정
		@
		private Timestamp createDate;
		
		
		@Column(nullable = false, length = 100)
		private String title;
		
		@Lob
		@Column(nullable = false)
		private String content;
		
		@CreationTimestamp
		private Timestamp createDate;
		
		
}
