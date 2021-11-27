package com.sparta.securityoauthlogin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Reply extends Timestamped {

    @Id
    @GeneratedValue
    private Long replyId;

    private String content;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_Id")
    private Boards boards;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Builder
    public Reply(Long replyId, String content, String username, Boards boards, User user) {
        this.replyId = replyId;
        this.content = content;
        this.username = username;
        this.boards = boards;
        this.user = user;
    }

    public void updateReply(String content) {
        this.content = content;
    }

    public void setBoardsReply(Boards boards){
        if (this.boards != null){
            this.boards.getReplyList().remove(this);
        }
        this.boards = boards;
        boards.getReplyList().add(this);
    }
}
