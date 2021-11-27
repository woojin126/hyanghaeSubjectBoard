package com.sparta.securityoauthlogin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ReplyRequestDto {
    private Long boardId;

    private String username;
    @NotBlank(message = "내용은 필수입니다")
    private String content;
}
