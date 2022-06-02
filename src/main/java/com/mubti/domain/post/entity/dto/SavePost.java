package com.mubti.domain.post.entity.dto;

import com.mbti.oauthlogin.api.entity.model.Comments;
import com.mbti.oauthlogin.api.entity.model.PostCategory;
import com.mbti.oauthlogin.api.entity.user.User;
import lombok.*;

import com.mbti.oauthlogin.api.entity.model.Posts;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavePost {
    private Long postNum;
    private User user;
    private String postTitle;
    private PostCategory postCategory;
    private String postContent;
    private LocalDateTime postDate;
    private Long views;
    private Long votes;
    private List<Comments> comments;

    public Posts toEntity(){
        Posts posts = Posts.builder()
                .postNum(postNum)
                .user(user)
                .postCategory(postCategory)
                .postContent(postContent)
                .postDate(postDate)
                .views(views)
                .votes(votes)
                .comments(comments)
                .build();

        return posts;
    }
}
