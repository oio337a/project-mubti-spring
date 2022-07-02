package com.mubti.domain.post.repository.custom;

import com.mubti.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    Page<Post> findAllByPostTitle(Pageable pageable, String categoryType, String keyword);
    Page<Post> findAllByPostContent(Pageable pageable, String categoryType, String keyword);
    Page<Post> findAllByUserAlias(Pageable pageable, String categoryType, String keyword);
    Page<Post> findAllByPostTitleOrPostContent(Pageable pageable, String categoryType, String keyword);
}
