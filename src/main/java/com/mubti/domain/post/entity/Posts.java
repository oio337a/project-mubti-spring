package com.mubti.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "POSTS")
public class Posts {
    @Id
    @Column(name = "POST_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postSeq;

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
    private PostCategoryType postCategoryType;

    @Column(name = "POST_CONTENT", columnDefinition = "TEXT")
    @NotNull
    private String postContent;

    @Column(name = "POST_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotNull
    private LocalDateTime postDate;

    @Column(name = "VIEW")
    @NotNull
    private Long view;

    @Column(name = "VOTE")
    @NotNull
    private Long vote;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comments> comments;

    public void updateTitleAndContent(PostRequestDto post) {
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
    }
}