package com.mubti.domain.post.entity.dto;

import com.mbti.oauthlogin.api.entity.model.Posts;
import com.mbti.oauthlogin.api.entity.model.PostCategory;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReadPost {
    private Long postNum;
    private String userAlias;
    private String postTitle;
    private PostCategory postCategory;
    private String postContent;
    private LocalDateTime postDate;
    private Long views;
    private Long votes;
    private List<ReadComment> comments;

    public ReadPost(Posts post) {
        this.postNum = post.getPostNum();
        this.userAlias = post.getUser().getUserAlias();
        this.postTitle = post.getPostTitle();
        this.postCategory = post.getPostCategory();
        this.postContent = post.getPostContent();
        this.postDate = post.getPostDate();
        this.views = post.getViews();
        this.votes = post.getVotes();
        this.comments = post.getComments().stream().map(ReadComment::new).collect(Collectors.toList());
    }
}
