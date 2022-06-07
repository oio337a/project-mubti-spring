package com.mubti.domain.post.repository;

import com.mubti.domain.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
