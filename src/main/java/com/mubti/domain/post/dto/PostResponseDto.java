package com.mubti.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    private String categoryType;
    private String postContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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
