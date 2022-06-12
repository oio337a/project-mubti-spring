package com.mubti.domain.post.entity;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LikeId implements Serializable {
    private String userId;
    private long postSeq;
}
