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
    @Query(value = "SELECT * FROM Post p WHERE p.categoryType LIKE %:categoryType% AND p.postTitle LIKE %:keyword%", nativeQuery = true)
    Page<Post> selectPostListByTitle(Pageable pageable, @Param("categoryType") String categoryType, @Param("keyword") String keyword);

    @Query(value = "SELECT * FROM Post p WHERE p.categoryType LIKE %:categoryType% AND p.postContent LIKE %:keyword%", nativeQuery = true)
    Page<Post> selectPostListByContent(Pageable pageable, @Param("categoryType") String categoryType, @Param("keyword") String keyword);

    @Query(value = "SELECT p.* FROM Post p INNER JOIN User u ON p.userSeq = u.userSeq WHERE p.categoryType LIKE %:categoryType% AND u.userAlias LIKE %:keyword%", nativeQuery = true)
    Page<Post> selectPostListByUserAlias(Pageable pageable, @Param("categoryType") String categoryType, @Param("keyword") String keyword);

    @Query(value = "SELECT * FROM Post p WHERE p.categoryType LIKE %:categoryType% AND (p.postContent LIKE %:keyword% OR p.postTitle LIKE %:keyword%)", nativeQuery = true)
    Page<Post> selectPostListByTitleContent(Pageable pageable, @Param("categoryType") String categoryType, @Param("keyword") String keyword);

}
