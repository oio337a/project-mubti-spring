package com.mubti.domain.post.repository;

import com.mubti.domain.post.entity.Post;
import com.mubti.domain.post.entity.CategoryType;
import com.mubti.domain.post.entity.SearchType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM Post p WHERE p.categoryType = :categoryType AND p.:searchType LIKE %:keyword%", nativeQuery = true)
    Page<Post> selectPostLIst(Pageable pageable, @Param("categoryType") String categoryType, @Param("searchType") String searchType, @Param("keyword") String keyword);
}
