package com.sparta.securityoauthlogin.service;

import com.sparta.securityoauthlogin.dto.BoardRequestDto;
import com.sparta.securityoauthlogin.model.Boards;
import com.sparta.securityoauthlogin.model.User;
import com.sparta.securityoauthlogin.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Boards saveReply(User user, BoardRequestDto boardRequestDto) {
        Boards boards = Boards.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .user(user)
                .build();

        return boardRepository.save(boards);
    }
}
