package com.sparta.securityoauthlogin.service;

import com.sparta.securityoauthlogin.dto.SignupRequestDto;
import com.sparta.securityoauthlogin.model.User;
import com.sparta.securityoauthlogin.model.UserRoleEnum;
import com.sparta.securityoauthlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Transactional
    public void registerUser(SignupRequestDto signupRequestDto) {
        String encoded = passwordEncoder.encode(signupRequestDto.getPassword());

        User user = User.builder()
                .username(signupRequestDto.getUsername())
                .email(signupRequestDto.getEmail())
                .password(encoded)
                .role(UserRoleEnum.USER)
                .build();

        userRepository.save(user);
    }

}

