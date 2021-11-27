package com.sparta.securityoauthlogin.model;

import com.sparta.securityoauthlogin.dto.SignupRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends Timestamped {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private String provider;

    private String providerId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Boards> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    public User(String username, String password, String email){
        this.username=username;
        this.password=password;
        this.email=email;
    }

    @Builder
    public User(String username, String password, String email, UserRoleEnum role, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

}
