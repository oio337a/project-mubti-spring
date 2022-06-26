package com.mubti.domain.post.entity;

import com.mubti.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VOTE")
public class Vote {
    @Id
    @Column(name = "VOTE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteSeq;

    @NotNull
    @Column(name = "USER_ID")
    private String userId;

    @NotNull
    @Column(name = "POST_SEQ")
    private long postSeq;

}
