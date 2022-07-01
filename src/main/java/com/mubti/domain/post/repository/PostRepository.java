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
    @Query(
            value = "SELECT * FROM post WHERE post_category LIKE %:categoryType% AND post_title LIKE %:keyword%",
            countQuery = "SELECT count(*) FROM post",
            nativeQuery = true)
    Page<Post> selectPostListByTitle(Pageable pageable, @Param("categoryType") String categoryType, @Param("keyword") String keyword);

    @Query(
            value = "SELECT * FROM post WHERE post_category LIKE %:categoryType% AND post_content LIKE %:keyword%",
            countQuery = "SELECT count(*) FROM post",
            nativeQuery = true)
    Page<Post> selectPostListByContent(Pageable pageable, @Param("categoryType") String categoryType, @Param("keyword") String keyword);

    @Query(
            value = "SELECT p.* FROM post p INNER JOIN user u ON p.user_seq = u.user_seq WHERE post_category LIKE %:categoryType% AND u.user_alias LIKE %:keyword%",
            countQuery = "SELECT count(*) FROM post",
            nativeQuery = true)
    Page<Post> selectPostListByUserAlias(Pageable pageable, @Param("categoryType") String categoryType, @Param("keyword") String keyword);

    @Query(
            value = "SELECT * FROM Post WHERE post_category LIKE %:categoryType% AND (post_title LIKE %:keyword% OR post_content LIKE %:keyword%)",
            countQuery = "SELECT count(*) FROM post",
            nativeQuery = true)
    Page<Post> selectPostListByTitleContent(Pageable pageable, @Param("categoryType") String categoryType, @Param("keyword") String keyword);

}
