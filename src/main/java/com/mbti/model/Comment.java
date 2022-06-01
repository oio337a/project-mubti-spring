package com.mbti.model;

import com.mbti.oauthlogin.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comment")
public class Comment {
    @ManyToOne
    @JoinColumn(name = "POST_NUM")
    private Post post;

    @JsonIgnore
    @Id
    @Column(name = "COMMENT_NUM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNum;

    @ManyToOne
    @JoinColumn(name = "USER_NAME")
    private User user;

    @Column(name = "COMMENT_DATE")
    @NotNull
    private LocalDateTime commentDate;

    @Column(name = "COMMENT_CONTENT", columnDefinition = "TEXT")
    @NotNull
    private String commentContent;

    public Comment(
            @NotNull User user,
            @NotNull String commentContent,
            @NotNull LocalDateTime commentDate
    ) {
        this.user = user;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }
}
