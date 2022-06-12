package com.mubti.domain.post.entity;

import com.mubti.domain.user.entity.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LIKE")
public class Like {
    @EmbeddedId
    private LikeId likeId;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    @MapsId("userId")
    private User user;

    @OneToMany
    @JoinColumn(name = "POST_SEQ")
    @MapsId("postSeq")
    private Posts posts;
    
}
