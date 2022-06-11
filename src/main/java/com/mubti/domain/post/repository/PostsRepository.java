package com.mubti.domain.post.repository;

import com.mubti.domain.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAll();
}
