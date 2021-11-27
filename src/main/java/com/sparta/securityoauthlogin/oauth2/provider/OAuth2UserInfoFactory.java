package com.sparta.securityoauthlogin.oauth2.provider;

import com.sparta.securityoauthlogin.model.Provider;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String,Object> attributes){

        if(registrationId.equalsIgnoreCase(Provider.google.name())){
            return new GoogleOAuth2UserInfo(attributes);
        }else if(registrationId.equalsIgnoreCase(Provider.kakao.name())){
            Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
            return new KakaoOAuth2UserInfo(kakao_account);
        }else{
            throw new IllegalArgumentException("죄송합니다 아직 지원하지않는 소셜입니다.");
        }
    }
}
