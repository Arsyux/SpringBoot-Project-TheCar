package com.arsyux.thecar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {

	//@Size(min = 10, max = 11, message = "휴대전화번호는 8글자로 입력하세요.")
	@NotNull(message = "휴대전화번호가 전달되지 않았습니다.")
	@NotBlank(message = "휴대전화번호는 필수 입력 항목입니다.")
	@Pattern(regexp = "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$", message = "휴대전화번호를 확인하여주세요.")
	private String phone;
	
}
