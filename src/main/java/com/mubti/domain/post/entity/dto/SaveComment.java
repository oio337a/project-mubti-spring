package com.mubti.domain.post.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mubti.domain.post.entity.Comments;
import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.user.entity.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveComment {
    private Posts posts;
    private Long commentNum;
    private User user;
    private String commentContent;
    private LocalDateTime commentDate;

    public Comments toEntity(){
        Comments comments = Comments.builder()
                .posts(posts)
                .commentNum(commentNum)
                .user(user)
                .commentContent(commentContent)
                .commentDate(commentDate)
                .build();

        return comments;
    }
}
