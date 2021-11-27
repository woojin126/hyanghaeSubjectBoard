package com.sparta.securityoauthlogin.valid;

import com.sparta.securityoauthlogin.dto.SignupRequestDto;
import com.sparta.securityoauthlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class AccountValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupRequestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj,  Errors errors) {

        SignupRequestDto signupRequestDto = (SignupRequestDto) obj;
        if (!((SignupRequestDto)obj).getPassword().equals(((SignupRequestDto)obj).getPasswordRepeat())){
            errors.rejectValue("passwordRepeat", "passwordRepeat","비밀번호가 일치하지 않습니다.");
        }else if (userRepository.existsByUsername(signupRequestDto.getUsername())){
            errors.rejectValue("username", "invalid.username", new Object[]{signupRequestDto.getUsername()}, "이미 사용중인 이름입니다");
        }else if (((SignupRequestDto)obj).getPassword().equals(((SignupRequestDto)obj).getUsername())){
            errors.rejectValue("password", "password","아이디와 비밀번호가 같을 수 없습니다.");
        }else if (userRepository.existsByEmail(signupRequestDto.getEmail())){
            errors.rejectValue("email", "invalid.mail", "이미 사용중인 이메일 입니다.");
        }
    }
}
