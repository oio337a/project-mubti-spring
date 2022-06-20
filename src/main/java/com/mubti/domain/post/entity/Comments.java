package com.mubti.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mubti.domain.user.entity.User;
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
@Table(name = "COMMENTS")
public class Comments {
    @ManyToOne
    @JoinColumn(name = "POST_SEQ")
    private Posts posts;

    @Id
    @Column(name = "COMMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentSeq;

    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @Column(name = "COMMENT_CONTENT", columnDefinition = "TEXT")
    @NotNull
    private String commentContent;
    @Column(name = "COMMENT_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotNull
    private LocalDateTime commentDate;

    public Comments(
            @NotNull Posts posts,
            @NotNull User user,
            @NotNull String commentContent,
            @NotNull LocalDateTime commentDate
    ) {
        this.posts = posts;
        this.commentSeq = null;
        this.user = user;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }
}
