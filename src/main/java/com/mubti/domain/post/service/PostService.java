package com.mubti.domain.post.service;

import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.post.dto.PostResponseDto;
import com.mubti.domain.post.entity.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<PostResponseDto> getPostList(Pageable pageable);
    Page<PostResponseDto> getPostListByCategory(Pageable pageable, CategoryType categoryType);
    PostResponseDto getPost(long id);
    void postPost(String userId, PostRequestDto postRequestDto);
    void putPost(long id, PostRequestDto postRequestDto);
    void deletePost(long id);
}
