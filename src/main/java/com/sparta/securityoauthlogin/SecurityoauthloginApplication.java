package com.sparta.securityoauthlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SecurityoauthloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityoauthloginApplication.class, args);
    }

}
