package com.mubti.domain.post.dto;

import com.mubti.domain.post.entity.Comment;
import com.mubti.domain.post.entity.Post;
import com.mubti.domain.post.entity.CategoryType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDto {
    private long postSeq;
    private String userId;
    private String userAlias;
    private String postTitle;
    private CategoryType categoryType;
    private String postContent;
    private LocalDateTime postDate;
    private Long view;
    private Long vote;
    private List<Comment> comment;

    public PostResponseDto(Post post) {
        this.postSeq = post.getPostSeq();
        this.userAlias = post.getUser().getUserAlias();
        this.userId = post.getUser().getUserId();
        this.postTitle = post.getPostTitle();
        this.categoryType = post.getCategoryType();
        this.postContent = post.getPostContent();
        this.postDate = post.getPostDate();
        this.view = post.getView();
        this.vote = post.getVote();
        this.comment = post.getComment();
    }
}
