package com.sparta.securityoauthlogin.model;



import com.sparta.securityoauthlogin.dto.SignupRequestDto;
import com.sparta.securityoauthlogin.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserTest {

    @Autowired
    UserService userService;
    @Nested
    @DisplayName("회원가입 요청")
    class CreateUserInfo {
        private String username;
        private String password;
        private String email;

        @BeforeEach
        void setup() {
            username = "woojinW1";
            password = "1234asdf";
            email = "woojin126@naver.com";
        }

        @Test
        @DisplayName("정상 케이스")
        void createUserInfo_Normal() {
            User user = new User(username, password, email);
            assertEquals(user.getUsername(), username);
            assertEquals(user.getPassword(), password);
            assertEquals(user.getEmail(), email);
        }


        @Test
        @DisplayName("패스워드 길이가 조건보다 짧을경우")
        void fail1() {
            password = "12";

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new User(username, password, email);
            });

            assertEquals("비밀번호는 4자이상의 비밀번호여야 합니다.", exception.getMessage());
        }

        @Test
        @DisplayName("비밀번호가 같을경우")
        void fail2(){
            String passworded = "12345";

            SignupRequestDto build = SignupRequestDto
                    .builder()
                    .username(username)
                    .password(password)
                    .passwordRepeat(passworded)
                    .email(email)
                    .build();


            Exception illegalArgumentException = assertThrows(NullPointerException.class, () -> userService.registerUser(build));
            if (illegalArgumentException.getMessage().equals("비밀번호가 같지 않습니다.")){
                assertEquals("비밀번호가 같지 않습니다.", illegalArgumentException.getMessage());
            }
        }

        @Test
        @DisplayName("회원 가입 중복")
        void fail3(){
            String username = "누리다g5";
            String password = "asdfasdf";
            String passwordRepeat = "asdfasdf";
            String email = "woojin126@naver.com";

            SignupRequestDto build = SignupRequestDto.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .passwordRepeat(passwordRepeat)
                    .build();

            Exception exception = assertThrows(IllegalArgumentException.class, () ->{
                userService.registerUser(build);
            });

            System.out.println(exception.getMessage());
        }
        @Nested
        @DisplayName("비밀번호")
        class Password {
            @Test
            @DisplayName("null")
            void fail1() {
                password = null;

                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new User(username, password, email);
                });

                assertEquals("최소 4자 이상, 닉네임과 같은 값이 포함되지 않아야 합니다.", exception.getMessage());
            }

            @Test
            @DisplayName("아이디를 포함하고 있을 때")
            void fail2() {
                password = "diddl1234";

                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new User(username, password, email);
                });

                assertEquals("최소 4자 이상, 닉네임과 같은 값이 포함되지 않아야 합니다.", exception.getMessage());
            }

            @Test
            @DisplayName("Password 길이가 부족할 때")
            void fail3() {
                username = "tjd";

                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new User(username, password, email);
                });

                assertEquals("최소 4자 이상, 닉네임과 같은 값이 포함되지 않아야 합니다.", exception.getMessage());
            }
        }
    }
}




