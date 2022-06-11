package com.mubti.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mubti.domain.user.entity.user.User;
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
@Table(name = "POSTS")
public class Posts {
    @JsonIgnore
    @Id
    @Column(name = "POST_NUM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNum;

    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @Column(name = "POST_TITLE", length = 128)
    @NotNull
    @Size(max = 128)
    private String postTitle;

    @Column(name = "POST_CATEROTY", length = 4)
    @Enumerated(EnumType.STRING)
    @NotNull
    private PostCategory postCategory;

    @Column(name = "POST_CONTENT", columnDefinition = "TEXT")
    @NotNull
    private String postContent;

    @Column(name = "POST_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotNull
    private LocalDateTime postDate;

    @Column(name = "VIEWS")
    @NotNull
    private Long views;

    @Column(name = "VOTES")
    @NotNull
    private Long votes;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comments> comments;

    public Posts(
            @NotNull User user,
            @NotNull @Size(max = 128) String postTitle,
            @NotNull PostCategory postCategory,
            @NotNull String postContent,
            @NotNull LocalDateTime postDate,
            @NotNull Long views,
            @NotNull Long votes
    ) {
        this.postNum = null;
        this.user = user;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postContent = postContent;
        this.postDate = postDate;
        this.views = views == null ? 0 : views;
        this.votes = votes == null ? 0 : votes;
    }
}