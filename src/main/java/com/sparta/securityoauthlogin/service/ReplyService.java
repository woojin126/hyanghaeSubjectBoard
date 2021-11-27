package com.sparta.securityoauthlogin.service;

import com.sparta.securityoauthlogin.dto.ReplyRequestDto;
import com.sparta.securityoauthlogin.model.Boards;
import com.sparta.securityoauthlogin.model.Reply;
import com.sparta.securityoauthlogin.model.User;
import com.sparta.securityoauthlogin.repository.BoardRepository;
import com.sparta.securityoauthlogin.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Reply save(ReplyRequestDto replyRequestDto, User user) {
        Long boardId = replyRequestDto.getBoardId();
        Boards boards = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("게시물아이디가 존재하지 않습니다"));
        Reply build = Reply.builder()
                .boards(boards)
                .user(user)
                .content(replyRequestDto.getContent())
                .username(replyRequestDto.getUsername())
                .build();
        return replyRepository.save(build);
    }

    @Transactional
    public void update(Long replyId, String content) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("댓글 아이디가 없습니다."));
        reply.updateReply(content);

        replyRepository.save(reply);
    }
}
