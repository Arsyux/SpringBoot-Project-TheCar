package com.arsyux.thecar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 유저 유효성 검사
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {

	// 비밀번호
	@NotNull(message = "비밀번호가 전달되지 않았습니다.")
	@NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
	@Size(min = 1, max = 20, message = "비밀번호는 1자 이상 20자 미만으로 입력해주세요.")
	private String password;
		
	// 이메일
	@NotNull(message = "이메일이 전달되지 않았습니다.")
	@NotBlank(message = "이메일은 필수 입력 항목입니다.")
	@Size(min = 1, max = 100, message = "이메일은 1자 이상 100자 미만으로 입력해주세요.")
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;

}
