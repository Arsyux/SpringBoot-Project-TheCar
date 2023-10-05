package com.arsyux.thecar.dto;

import javax.validation.constraints.Email;
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
	
	//@NotNull(message = "이름이 전달되지 않았습니다.")
	//@NotBlank(message = "이름은 필수 입력 항목입니다.")
	//private String name;
	
	//@NotNull(message = "성별이 전달되지 않았습니다.")
	//@NotBlank(message = "성별은 필수 입력 항목입니다.")
	//private String gender;
	
	//@NotNull(message = "생년월일이 전달되지 않았습니다.")
	//@NotBlank(message = "생년월일은 필수 입력 항목입니다.")
	//private String birthDate;
	
	//@NotNull(message = "휴대폰번호가 전달되지 않았습니다.")
	//@NotBlank(message = "휴대폰번호는 필수 입력 항목입니다.")
	//private String phone;
	
	@NotNull(message = "Email이 전달되지 않았습니다.")
	@NotBlank(message = "Email은 필수 입력 항목입니다.")
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;
	
	//@NotNull(message = "주소가 전달되지 않았습니다.")
	//@NotBlank(message = "주소는 필수 입력 항목입니다.")
	//private String address;
	
}
