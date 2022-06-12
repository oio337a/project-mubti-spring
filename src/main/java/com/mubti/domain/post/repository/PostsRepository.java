package com.mubti.domain.post.repository;

import com.mubti.domain.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAll();
    /*@Modifying
    @Query("update POSTS p set p.VIEW = p.VIEW + 1 where p.POST_SEQ = :id")
    int updateView(Long id);*/
}
