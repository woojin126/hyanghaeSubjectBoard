package com.sparta.securityoauthlogin.auth;

import com.sparta.securityoauthlogin.model.User;
import com.sparta.securityoauthlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("아이디가 없습니다"));
        
        return new CustomUserDetails(user);
    }
}
