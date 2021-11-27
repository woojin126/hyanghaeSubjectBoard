package com.sparta.securityoauthlogin.controller;

import com.sparta.securityoauthlogin.auth.CustomUserDetails;
import com.sparta.securityoauthlogin.dto.SignupRequestDto;
import com.sparta.securityoauthlogin.service.UserService;
import com.sparta.securityoauthlogin.valid.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final AccountValidator accountValidator;
    private final UserService userService;


    //로그인 이동
    @GetMapping(value = "/user/login")
    public String logins(Model model,@AuthenticationPrincipal CustomUserDetails customUserDetails){
        if (customUserDetails == null){
            model.addAttribute("user", true);
        }else{
            model.addAttribute("user", false);
        }
        return "login";
    }

    //로그인 에러
    @GetMapping("/user/login/error")
    public String loginError(Model model, @AuthenticationPrincipal CustomUserDetails userPrincipal){
        if (userPrincipal == null){
            model.addAttribute("user","true");
        }else{
            model.addAttribute("user",false);
        }
        model.addAttribute("loginError", true);

        return "login";
    }
    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signupGet(Model model,@ModelAttribute SignupRequestDto signupRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (customUserDetails == null){
            model.addAttribute("user", true);
        }else{
            model.addAttribute("user", false);
        }
        return "signup";
    }

    //회원가입
    @PostMapping("/user/signup")
    public String signupPost(@Valid SignupRequestDto signupRequestDto, BindingResult bindingResult, Errors error){
        accountValidator.validate(signupRequestDto,error);
        if (bindingResult.hasErrors()){
            return "signup";
        }
        userService.registerUser(signupRequestDto);
        return "redirect:/user/login";
    }
}
