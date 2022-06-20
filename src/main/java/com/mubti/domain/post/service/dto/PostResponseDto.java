package com.mubti.domain.post.service.dto;

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

    public PostResponseDto(Posts postList) {
        this.postSeq = postList.getPostSeq();
        this.userAlias = postList.getUser().getUserAlias();
        this.userId = postList.getUser().getUserId();
        this.postTitle = postList.getPostTitle();
        this.postCategoryType = postList.getPostCategoryType();
        this.postContent = postList.getPostContent();
        this.postDate = postList.getPostDate();
        this.view = postList.getView();
        this.vote = postList.getVote();
        this.comments = postList.getComments();
    }
}
