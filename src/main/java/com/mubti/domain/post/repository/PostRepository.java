package com.mubti.domain.post.repository;

import com.mubti.domain.post.entity.Post;
import com.mubti.domain.post.repository.custom.PostCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

}
