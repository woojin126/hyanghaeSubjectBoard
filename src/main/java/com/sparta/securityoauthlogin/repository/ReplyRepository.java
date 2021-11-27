package com.sparta.securityoauthlogin.repository;

import com.sparta.securityoauthlogin.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    @Query(value = "select r from Reply r where r.boards.boardId =:boardId")
    List<Reply> findAllReply(@Param("boardId") Long boardId);
    List<Reply> findByBoards_BoardIdOrderByModifiedAtDesc(Long id);
}
