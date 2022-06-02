package com.mubti.domain.post.entity;

import com.mbti.oauthlogin.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Post")
public class Posts {
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comments> comments;

    public Posts(
            @NotNull User user,
            @NotNull @Size(max = 512) String postTitle,
            @NotNull PostCategory postCategory,
            @NotNull String postContent,
            @NotNull LocalDateTime postDate,
            @NotNull Long views,
            @NotNull Long votes
    ) {
        this.postNum = postNum;
        this.user = user;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postContent = postContent;
        this.postDate = postDate;
        this.views = views == null ? 0 : views;
        this.votes = votes == null ? 0 : votes;
        this.comments = comments;
    }
}