package com.mbti.oauthlogin.api.entity.model.dto;

import com.mbti.oauthlogin.api.entity.model.Comments;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadComment {
    private Long postNum;
    private Long commentNum;
    private String userAlias;
    private String commentContent;
    private LocalDateTime commentDate;

    public ReadComment(Comments comment) {
        this.postNum = comment.getPosts().getPostNum();
        this.commentNum = comment.getCommentNum();
        this.userAlias = comment.getUser().getUserAlias();
        this.commentContent = comment.getCommentContent();
        this.commentDate = comment.getCommentDate();
    }
}
