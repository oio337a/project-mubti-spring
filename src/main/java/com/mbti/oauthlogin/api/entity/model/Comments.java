package com.mbti.oauthlogin.api.entity.model;

import com.mbti.oauthlogin.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Comment")
public class Comments {
    @ManyToOne
    @JoinColumn(name = "POST_NUM")
    private Posts posts;

    @JsonIgnore
    @Id
    @Column(name = "COMMENT_NUM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNum;

    @ManyToOne
    @JoinColumn(name = "USER_NAME")
    private User user;

    @Column(name = "COMMENT_CONTENT", columnDefinition = "TEXT")
    @NotNull
    private String commentContent;
    @Column(name = "COMMENT_DATE")
    @NotNull
    private LocalDateTime commentDate;

    public Comments(
            @NotNull User user,
            @NotNull String commentContent,
            @NotNull LocalDateTime commentDate
    ) {
        this.posts = posts;
        this.commentNum = commentNum;
        this.user = user;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }
}
