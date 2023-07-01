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
	@Size(min = 5, max = 10, message = "로그인 아이디는 다섯 글자 이상 10자 이하로 입력하세요.")
	private String username;
	
	@NotNull(message = "Password 파라미터가 전달되지 않았습니다.")
	@NotBlank(message = "Password은 필수 입력 항목입니다.")
	@Size(min = 1, max = 20, message = "Password는 한 글자 이상 20자 이하로 입력하세요.")
	private String password;
	
	@NotNull(message = "이름이 전달되지 않았습니다.")
	@NotBlank(message = "이름은 필수 입력 항목입니다.")
	@Size(min = 1, max = 20, message = "이름은 한 글자 이상 20자 이하로 입력하세요.")
	private String realname;
	
	@NotNull(message = "생년월일이 전달되지 않았습니다.")
	@NotBlank(message = "생년월일은 필수 입력 항목입니다.")
	@Size(min = 8, max = 8, message = "생년월일은 8글자로 입력하세요.")
	private String birthdate;
	
	@NotNull(message = "휴대전화번호가 전달되지 않았습니다.")
	@NotBlank(message = "휴대전화번호는 필수 입력 항목입니다.")
	@Size(min = 10, max = 11, message = "휴대전화번호는 8글자로 입력하세요.")
	private String phone;

	@NotNull(message = "성별이 전달되지 않았습니다.")
	@NotBlank(message = "성별은 필수 입력 항목입니다.")
	private String gender;
}
