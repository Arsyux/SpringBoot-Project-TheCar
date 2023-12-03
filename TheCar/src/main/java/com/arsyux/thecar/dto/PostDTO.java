package com.arsyux.thecar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 게시글 유효성 검사
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

	// 게시글 작성시 유효성 검사
	public interface PostValidationGroup { }
	
	// 제목
	@NotNull(groups = PostValidationGroup.class, message = "제목이 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "제목은 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "제목은 1자 이상 100자 미만으로 입력해주세요.", min = 1, max = 100)
	private String title;
	
	// 내용
	@NotNull(groups = PostValidationGroup.class, message = "내용이 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "내용은 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "내용은 1자 이상 2000자 미만으로 입력해주세요.", min = 1, max = 2000)
	private String content;
	
	// 출발지 우편번호
	@NotNull(groups = PostValidationGroup.class, message = "출발지 우편번호가 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "출발지 우편번호는 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "출발지 우편번호는 1자 이상 200자 미만으로 입력해주세요.", min = 1, max = 200)
	private String departures_postcode;
	
	// 출발지 주소
	@NotNull(groups = PostValidationGroup.class, message = "출발지 주소가 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "출발지 주소는 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "출발지 주소는 1자 이상 200자 미만으로 입력해주세요.", min = 1, max = 200)
	private String departures_address;
	
	// 출발지 상세 주소
	@NotNull(groups = PostValidationGroup.class, message = "출발지 상세 주소가 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "출발지 상세 주소는 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "출발지 상세 주소는 1자 이상 200자 미만으로 입력해주세요.", min = 1, max = 200)
	private String departures_detailAddress;
	
	// 출발지 참조 주소
	@NotNull(groups = PostValidationGroup.class, message = "출발지 참조 주소가 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "출발지 참조 주소는 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "출발지 참조 주소는 1자 이상 200자 미만으로 입력해주세요.", min = 1, max = 200)
	private String departures_extraAddress;
	
	// 도착지 우편 번호
	@NotNull(groups = PostValidationGroup.class, message = "도착지 우편 번호가 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "도착지 우편 번호는 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "도착지 우편 번호는 1자 이상 200자 미만으로 입력해주세요.", min = 1, max = 200)
	private String arrivals_postcode;
	
	// 도착지 주소
	@NotNull(groups = PostValidationGroup.class, message = "도착지 주소가 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "도착지 주소는 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "도착지 주소는 1자 이상 200자 미만으로 입력해주세요.", min = 1, max = 200)
	private String arrivals_address;
	
	// 도착지 상세 주소
	@NotNull(groups = PostValidationGroup.class, message = "도착지 상세 주소가 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "도착지 상세 주소는 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "도착지 상세 주소는 1자 이상 200자 미만으로 입력해주세요.", min = 1, max = 200)
	private String arrivals_detailAddress;
	
	// 도착지 참조 주소
	@NotNull(groups = PostValidationGroup.class, message = "도착지 참조 주소가 전달되지 않았습니다.")
	@NotBlank(groups = PostValidationGroup.class, message = "도착지 참조 주소는 필수 입력 항목입니다.")
	@Size(groups = PostValidationGroup.class, message = "도착지 참조 주소는 1자 이상 200자 미만으로 입력해주세요.", min = 1, max = 200)
	private String arrivals_extraAddress;
}
