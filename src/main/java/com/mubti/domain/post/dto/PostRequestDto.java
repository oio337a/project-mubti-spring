package com.mubti.domain.post.dto;

import com.mubti.domain.post.entity.PostCategoryType;
import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.user.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostRequestDto {
    private User user;
    private String postTitle;
    private PostCategoryType postCategoryType;
    private String postContent;

    public Posts toEntity(){
        return Posts.builder()
                .postSeq(null)
                .user(user)
                .postTitle(postTitle)
                .postCategoryType(postCategoryType)
                .postContent(postContent)
                .postDate(LocalDateTime.now())
                .view(0L)
                .vote(0L)
                .comments(null)
                .build();
    }
}
