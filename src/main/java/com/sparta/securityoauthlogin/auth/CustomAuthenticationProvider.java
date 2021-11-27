package com.sparta.securityoauthlogin.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/*인터페이스는 화면에서 입력한 로그인 정보와 DB에서 가져온 사용자의 정보를 비교해주는 인터페이스이다.
해당 인터페이스에 오버라이드되는 authenticate() 메서드는 화면에서 사용자가 입력한 로그인 정보를 담고 있는 Authentication 객체를 가지고 있다.
*/
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService principalDetailsService;
    private final BCryptPasswordEncoder encodePassword;


    //사용자가 입력한 form은 authentication 으로 , db정보가져오는것은 loadUserByname();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString(); //crudential 아이디, 비번 같은것들

        UserDetails user = principalDetailsService.loadUserByUsername(username);

        //BCryptPasswordEncoder 객체에서 사용 가능한 메소드는 encode, matches로, 각각 평문 해시화 기능과 해시결과 일치여부 확인 기능을 제공한다.
        if (user == null || !username.equals(user.getUsername()) || !encodePassword.matches(password,user.getPassword())){
            throw new BadCredentialsException("아이디 비밀번호가 일치하지 않습니다.");
        }

        //성공시 인증된 토큰을 생성하여 반환
        //이시점에 SecurityContextHolder.getContext).setAuthentication에 저장
        return new UsernamePasswordAuthenticationToken(user, password,user.getAuthorities());
    }


    // 스프링 Security가 요구하는 UsernamePasswordAuthenticationToken 타입이 맞는지 확인
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
