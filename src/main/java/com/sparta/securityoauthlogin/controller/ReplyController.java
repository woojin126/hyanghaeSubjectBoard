package com.sparta.securityoauthlogin.controller;

import com.sparta.securityoauthlogin.auth.CustomUserDetails;
import com.sparta.securityoauthlogin.dto.ReplyRequestDto;
import com.sparta.securityoauthlogin.model.Boards;
import com.sparta.securityoauthlogin.model.Reply;
import com.sparta.securityoauthlogin.repository.BoardRepository;
import com.sparta.securityoauthlogin.repository.ReplyRepository;
import com.sparta.securityoauthlogin.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;


    //댓글 조회
    @GetMapping("/reply/write")
    public String replyGet(@RequestParam("boardId") Long boardId, Model model) {
        List<Reply> replyAll = replyRepository.findAllReply(boardId);
        Boards boards = boardRepository.findById(boardId).get();

        model.addAttribute("boards",boards);
        model.addAttribute("replyList", replyAll);
        return "detail";
    }

 //댓글 저장
    @PostMapping("/reply/write/{id}")
    public String replyPost(@PathVariable Long id,ReplyRequestDto replyRequestDto,@AuthenticationPrincipal CustomUserDetails principalDetails) {

        replyService.save(replyRequestDto, principalDetails.getUser());
        return "redirect:/board/detail/{id}";
    }

    //댓글삭제
    @PostMapping("/reply/delete/{boardId}/{replyId}")
    public String deleteReply(@PathVariable Long replyId){
        replyRepository.deleteById(replyId);
        return "redirect:/board/detail/{boardId}";
    }

//댓글 수정페이지 이동
    @GetMapping("/reply/modify/{replyId}/{boardId}")
    public String replyModifiedPage(@PathVariable Long replyId,@PathVariable Long boardId, Model model, @ModelAttribute ReplyRequestDto replyRequestDto){
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("댓글 아이디가 없습니다"));
        model.addAttribute("reply", reply);
        model.addAttribute("boardId",boardId);
        return "reply";
    }
//댓글 수정
    @PostMapping("/reply/modify/update/{replyId}/{boardId}")
    public String replyModifiedPagePut(@PathVariable Long replyId,ReplyRequestDto replyRequestDto){
        replyService.update(replyId,replyRequestDto.getContent());

        return "redirect:/board/detail/{boardId}";
    }
}
