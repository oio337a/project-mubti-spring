package com.mbti.model;

import com.mbti.oauthlogin.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Post")
public class Post {
    @JsonIgnore
    @Id
    @Column(name = "POST_NUM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNum;

    @ManyToOne
    @JoinColumn(name = "USER_NAME")
    private User user;

    @Column(name = "POST_TITLE", length = 512)
    @NotNull
    @Size(max = 512)
    private String postTitle;

    @Column(name = "POST_CATEROTY", length = 4)
    @Enumerated(EnumType.STRING)
    @NotNull
    private PostCategory postCategory;

    @Column(name = "POST_CONTENT", columnDefinition = "TEXT")
    @NotNull
    private String postContent;

    @Column(name = "POST_DATE")
    @NotNull
    private LocalDateTime postDate;

    @Column(name = "VIEWS")
    @NotNull
    private Long views;

    @Column(name = "VOTES")
    @NotNull
    private Long votes;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "POST_NUM")
    private List<Comment> comment;

    public Post(
            @NotNull User user,
            @NotNull @Size(max = 512) String postTitle,
            @NotNull PostCategory postCategory,
            @NotNull String postContent,
            @NotNull LocalDateTime postDate,
            @NotNull Long views,
            @NotNull Long votes
    ) {
        this.user = user;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postContent = postContent;
        this.postDate = postDate;
        this.views = views == null ? 0 : views;
        this.votes = votes == null ? 0 : votes;
    }
}