package com.arsyux.thecar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsignmentDTO {

	@NotNull(message = "Title이 전달되지 않았습니다.")
	@NotBlank(message = "Title은 필수 입력 항목입니다.")
	private String title;
	
	@NotNull(message = "Content이 전달되지 않았습니다.")
	@NotBlank(message = "Content는 필수 입력 항목입니다.")
	private String content;
		
}
