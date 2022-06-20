package com.mubti.domain.post.repository;

import com.mubti.domain.post.entity.Post;
import com.mubti.domain.post.entity.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCategoryTypeContaining(Pageable pageable, CategoryType category);
    /*Page<Post> findAllByPostTitleAndPostContentContaining(Pageable pageable, String keyword);
    Page<Post> findAllByPostTitleContaining(Pageable pageable, String keyword);
    Page<Post> findALlByPostContentContaining(Pageable pageable, String keyword);
    Page<Post> findAllByUserAliasContaining(Pageable pageable, String keyword);
*/
}
