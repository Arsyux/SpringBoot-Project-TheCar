package com.arsyux.thecar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 유저 유효성 검사
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	// 회원 가입시 유효성 검사
	public interface InsertUserValidationGroup { }
	
	// 회원 정보 수정시 유효성 검사
	public interface UpdateUserValidationGroup { }
	
	// 아이디 중복 체크할 때 유효성 검사
	public interface UsernameCheckValidationGroup  { }
	
	// 휴대폰 번호 중복 체크할 때 유효성 검사
	public interface PhoneCheckValidationGroup { }
	
	// 이메일 중복 체크할 때 유효성 검사
	public interface EmailCheckValidationGroup { }
	
	// 로그인 아이디
	@NotNull(groups = { InsertUserValidationGroup.class, UsernameCheckValidationGroup.class }, message = "로그인 아이디가 전달되지 않았습니다.")
	@NotBlank(groups = { InsertUserValidationGroup.class, UsernameCheckValidationGroup.class }, message = "로그인 아이디는 필수 입력 항목입니다.")
	@Size(groups = { InsertUserValidationGroup.class, UsernameCheckValidationGroup.class }, 
			message = "로그인 아이디는 1자 이상 20자 미만으로 입력해주세요.", min = 1, max = 20)
	private String username;

	// 비밀번호
	@NotNull(groups = { InsertUserValidationGroup.class, UpdateUserValidationGroup.class }, message = "비밀번호가 전달되지 않았습니다.")
	@NotBlank(groups = { InsertUserValidationGroup.class, UpdateUserValidationGroup.class }, message = "비밀번호는 필수 입력 항목입니다.")
	@Size(groups = { InsertUserValidationGroup.class, UpdateUserValidationGroup.class }, message = "비밀번호는 1자 이상 20자 미만으로 입력해주세요.", min = 1, max = 20)
	private String password;
	
	// 이름
	@NotNull(groups = InsertUserValidationGroup.class, message = "이름이 전달되지 않았습니다.")
	@NotBlank(groups = InsertUserValidationGroup.class, message = "이름은 필수 입력 항목입니다.")
	@Size(groups = InsertUserValidationGroup.class, message = "이름은 1자 이상 20자 미만으로 입력해주세요.", min = 1, max = 20)
	private String name;
	
	// 생년월일 8자리
	@NotNull(groups = InsertUserValidationGroup.class, message = "생년월일이 전달되지 않았습니다.")
	@NotBlank(groups = InsertUserValidationGroup.class, message = "생년월일은 필수 입력 항목입니다.")
	@Pattern(groups = InsertUserValidationGroup.class, regexp = "^(19|20)([0-9]{2})(0[0-9]|1[0-2])((0|1|2)[0-9]|3[0-1])$",
				message = "생년월일을 정확히 입력해주세요.")
	private String birth;
	
	@NotNull(groups = InsertUserValidationGroup.class, message = "성별이 전달되지 않았습니다.")
	@NotBlank(groups = InsertUserValidationGroup.class, message = "성별은 필수 입력 항목입니다.")
	@Pattern(groups = InsertUserValidationGroup.class, regexp = "^[W|M]$")
	private String gender;
	
	// 휴대폰
	@NotNull(groups = { InsertUserValidationGroup.class, PhoneCheckValidationGroup.class }, message = "휴대폰 번호가 전달되지 않았습니다.")
	@NotBlank(groups = { InsertUserValidationGroup.class, PhoneCheckValidationGroup.class }, message = "휴대폰 번호는 필수 입력 항목입니다.")
	@Pattern(groups = { InsertUserValidationGroup.class, PhoneCheckValidationGroup.class },
				regexp = "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$", message = "휴대폰 번호를 정확히 입력해주세요.")
	private String phone;
	
	// 이메일
	@NotNull(groups = { InsertUserValidationGroup.class, EmailCheckValidationGroup.class }, message = "이메일이 전달되지 않았습니다.")
	@NotBlank(groups = { InsertUserValidationGroup.class, EmailCheckValidationGroup.class }, message = "이메일은 필수 입력 항목입니다.")
	@Size(groups = { InsertUserValidationGroup.class, EmailCheckValidationGroup.class }, message = "이메일은 1자 이상 100자 미만으로 입력해주세요.", min = 1, max = 100)
	@Email(groups = { InsertUserValidationGroup.class, EmailCheckValidationGroup.class }, message = "이메일 형식이 아닙니다.")
	private String email;
	
}
