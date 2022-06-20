package com.mubti.domain.post.dto;

import com.mubti.domain.post.entity.Comments;
import com.mubti.domain.post.entity.PostCategoryType;
import com.mubti.domain.post.entity.Posts;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDto {
    private long postSeq;
    private String userId;
    private String userAlias;
    private String postTitle;
    private PostCategoryType postCategoryType;
    private String postContent;
    private LocalDateTime postDate;
    private Long view;
    private Long vote;
    private List<Comments> comments;

    public PostResponseDto(Posts post) {
        this.postSeq = post.getPostSeq();
        this.userAlias = post.getUser().getUserAlias();
        this.userId = post.getUser().getUserId();
        this.postTitle = post.getPostTitle();
        this.postCategoryType = post.getPostCategoryType();
        this.postContent = post.getPostContent();
        this.postDate = post.getPostDate();
        this.view = post.getView();
        this.vote = post.getVote();
        this.comments = post.getComments();
    }
}
