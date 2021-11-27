package com.sparta.securityoauthlogin.oauth2;

import com.sparta.securityoauthlogin.auth.CustomUserDetails;
import com.sparta.securityoauthlogin.model.User;
import com.sparta.securityoauthlogin.model.UserRoleEnum;
import com.sparta.securityoauthlogin.oauth2.provider.OAuth2UserInfo;
import com.sparta.securityoauthlogin.oauth2.provider.OAuth2UserInfoFactory;
import com.sparta.securityoauthlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    @Lazy
    public CustomOAuth2UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    // oauth2 client 라이브러리덕에 code 인증 인가 부분이 skip 바로 카카오정보 획득가능
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        System.out.println(oAuth2User.getAttributes());

        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new NullPointerException("not found Email");
        }

        User userEntity = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        if (userEntity == null) {
            User user = User.builder()
                    .username(oAuth2UserInfo.getName())
                    .password(bCryptPasswordEncoder.encode("2134"))
                    .email(oAuth2UserInfo.getEmail())
                    .role(UserRoleEnum.USER)
                    .provider(oAuth2UserRequest.getClientRegistration().getRegistrationId())
                    .build();

            userEntity = userRepository.save(user);
            return new CustomUserDetails(userEntity, oAuth2User.getAttributes());
        } else {
            return new CustomUserDetails(userEntity, oAuth2User.getAttributes());
        }

    }
}