package com.arsyux.thecar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@NotNull(message = "Username이 전달되지 않았습니다.")
	@NotBlank(message = "Username은 필수 입력 항목입니다.")
	@Size(min = 1, max=20, message = "Username은 한 글자 이상 20자 미만으로 입력하세요.")
	private String username;

	@NotNull(message = "Password가 전달되지 않았습니다.")
	@NotBlank(message = "Password는 필수 입력 항목입니다.")
	@Size(min = 1, max=20, message = "Password는 한 글자 이상 20자 미만으로 입력하세요.")
	private String password;
	
}
