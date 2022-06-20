package com.mubti.domain.post.repository;

import com.mubti.domain.post.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
 /*   Page<Posts> findAllByPostCategory(Pageable pageable, String category);
    *//*Page<Posts> findAllByPostTitleAndPostContentContaining(Pageable pageable, String keyword);
    Page<Posts> findAllByPostTitleContaining(Pageable pageable, String keyword);
    Page<Posts> findALlByPostContentContaining(Pageable pageable, String keyword);
    Page<Posts> findAllByUserAliasContaining(Pageable pageable, String keyword);
*/

    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.postSeq = :id")
    int updateView(Long id);
}
