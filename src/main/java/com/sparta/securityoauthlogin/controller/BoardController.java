package com.sparta.securityoauthlogin.controller;

import com.sparta.securityoauthlogin.auth.CustomUserDetails;
import com.sparta.securityoauthlogin.dto.BoardRequestDto;
import com.sparta.securityoauthlogin.model.Boards;
import com.sparta.securityoauthlogin.model.Reply;
import com.sparta.securityoauthlogin.model.User;
import com.sparta.securityoauthlogin.repository.BoardRepository;
import com.sparta.securityoauthlogin.repository.ReplyRepository;
import com.sparta.securityoauthlogin.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;


    //게시글 전체조회
    @GetMapping("/")
    public String mainPage(Model model,@ModelAttribute BoardRequestDto boardRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if (customUserDetails == null){
            model.addAttribute("users", true);
        }else{
            model.addAttribute("users", false);
        }
        List<Boards> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        model.addAttribute("boards",boardList);
        return "index";
    }

    //게시글 정보 저장
    @PostMapping("/board")
    public String mainPagePost(@AuthenticationPrincipal CustomUserDetails principalDetails, @Valid BoardRequestDto boardRequestDto , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "index";
        }
        User user = principalDetails.getUser();
        boardService.saveReply(user, boardRequestDto);

        return "redirect:/";
    }

    //상세페이지 게시판 아이디 들고 이동
    @GetMapping("/board/detail/{boardId}")
    public String detailPage(@PathVariable("boardId") Long boardId, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        Boards boardInfo = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("존재하지 않는 아이디"));
        List<Reply> replyList = replyRepository.findByBoards_BoardIdOrderByModifiedAtDesc(boardId);

        if (customUserDetails == null){
            model.addAttribute("user", true);
        }else{
            model.addAttribute("user", false);
        }

        model.addAttribute("boards",boardInfo);
        model.addAttribute("replyList",replyList);
        model.addAttribute("editcomment",new Reply());
        model.addAttribute("postcomment",new Reply());
        return "detail";
    }

}
