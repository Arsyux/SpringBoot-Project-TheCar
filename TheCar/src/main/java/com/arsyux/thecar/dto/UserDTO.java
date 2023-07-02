package com.arsyux.thecar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@NotNull(message = "로그인 아이디가 전달되지 않았습니다.")
	@NotBlank(message = "로그인 아이디는 필수 입력 항목입니다.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "로그인 아이디를 확인하여주세요.")
	@Size(min = 5, max = 10, message = "로그인 아이디는 5자 이상 10자 이하로 입력하세요.")
	private String username;
	
	@NotNull(message = "비밀번호가 전달되지 않았습니다.")
	@NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "비밀번호는 영문, 숫자, 특수문자를 포함하여주세요.")
	@Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하로 입력하세요.")
	private String password;
	
	@NotNull(message = "이름이 전달되지 않았습니다.")
	@NotBlank(message = "이름은 필수 입력 항목입니다.")
	@Pattern(regexp = "^[가-힣]+$", message = "이름을 확인하여주세요.")
	@Size(min = 2, max = 30, message = "이름은 2자 이상 30자 이하로 입력하세요.")
	private String realname;
	
	@NotNull(message = "생년월일이 전달되지 않았습니다.")
	@NotBlank(message = "생년월일은 필수 입력 항목입니다.")
	@Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$", message = "생년월일을 확인하여주세요.")
	private String birthdate;
	
	@NotNull(message = "성별이 전달되지 않았습니다.")
	@NotBlank(message = "성별은 필수 입력 항목입니다.")
	private String gender;
	
	@NotNull(message = "휴대전화번호가 전달되지 않았습니다.")
	@NotBlank(message = "휴대전화번호는 필수 입력 항목입니다.")
	@Pattern(regexp = "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$", message = "휴대전화번호를 확인하여주세요.")
	private String phone;
}
