package com.mubti.domain.post.dto;

import com.mubti.domain.post.entity.Post;
import com.mubti.domain.post.entity.CategoryType;
import com.mubti.domain.user.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostRequestDto {
    private User user;
    private String postTitle;
    private CategoryType categoryType;
    private String postContent;

    public Post toEntity(){
        return Post.builder()
                .postSeq(null)
                .user(user)
                .postTitle(postTitle)
                .categoryType(categoryType)
                .postContent(postContent)
                .postDate(LocalDateTime.now())
                .view(0L)
                .vote(0L)
                .comment(null)
                .build();
    }
}
