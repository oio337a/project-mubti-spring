package com.mubti.domain.post.repository;

import com.mubti.domain.post.entity.Post;
import com.mubti.domain.post.entity.Vote;
import com.mubti.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByUserIdAndPostSeq(String userId, long postSeq);
}
