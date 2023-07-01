package com.arsyux.thecar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.arsyux.thecar.domain.OAuthType;
import com.arsyux.thecar.domain.RoleType;
import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.persistence.UserRepository;

@Service
public class OAuth2UserDetailsServiceImpl extends DefaultOAuth2UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${google.default.password}")
	private String googlePassword;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// 액세스 토큰이 저장된 userRequest를 이용하여 구글로부터 회원 정보를 받아온다.
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		// 구글이 전달해준 정보를 바탕으로 회원 정보를 구성
		String providerId = oauth2User.getAttribute("sub");
		String email = oauth2User.getAttribute("email");
		String username = email + "_" + providerId;
		String password = passwordEncoder.encode(googlePassword);
		
		// 회원가입이 되어있는 사용자인지 확인
		User findUser = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		
		if(findUser.getUsername() == null) {
			findUser = User.builder()
					   .username(username)
					   .password(password)
					   .role(RoleType.USER)
					   .oauth(OAuthType.GOOGLE)
					   .build();
			userRepository.save(findUser);
		}
		
		return new UserDetailsImpl(findUser, oauth2User.getAttributes());
	}
	
}
