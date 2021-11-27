package com.sparta.securityoauthlogin.model;

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
public class Boards extends Timestamped {

    @Id
    @GeneratedValue
    private Long boardId;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "boards", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @Builder
    public Boards(Long boardId,String title, String content, User user) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
