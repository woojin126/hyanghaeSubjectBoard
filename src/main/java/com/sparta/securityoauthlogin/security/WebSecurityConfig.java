package com.sparta.securityoauthlogin.security;

import com.sparta.securityoauthlogin.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOauth2UserService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
// h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()

// image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
// css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
// 회원 관리 처리 API 전부를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                .antMatchers(HttpMethod.GET,"/board/detail/{boardId}").permitAll()
                .antMatchers(HttpMethod.GET,"/**").permitAll()
                .antMatchers(HttpMethod.GET,"/user/login").permitAll()
// 그 외 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
// [로그인 기능]
                .formLogin()
// 로그인 View 제공 (GET /user/login)
                .loginPage("/user/login")
// 로그인 처리 (POST /user/login)
                .loginProcessingUrl("/user/login")// /login이 호출이되면 시큐리티가 낚아채서 대신 로그인을 진행해준다
// 로그인 처리 후 성공 시 URL
                .defaultSuccessUrl("/")//컨트롤러에 /login을 안만들어도 대신해준다
// 로그인 처리 후 실패 시 URL
                .failureUrl("/user/login/error")
                .permitAll()
                .and()
// [로그아웃 기능]
                .logout()
// 로그아웃 요청 처리 URL
                .logoutUrl("/user/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .oauth2Login() //oauth2Login 설정 시작
                .loginPage("/user/login")//google login을 할 기본 페이지, 구글로그인후의 후처리가 필요함
                .userInfoEndpoint()// oauth2Login 성공 이후의 설정을 시작 tip) 코드X, (엑세스토큰+사용자프로필정보(o))한번에 받아줌 => Oauth-Client라는 라이브러리의 엄청난 기능
                .userService(customOauth2UserService);//customOAuth2UserService(PrincipalOauth2UserService) => 구글로그인이 완료된 뒤의 후처리가 필요함, TIP) 코드x (액세스토큰 + 사용자프로필정보 O 바로받아줌) ouath2 라이브러리 특징

    }

}