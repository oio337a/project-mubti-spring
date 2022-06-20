package com.mubti.domain.post.service;

import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.post.dto.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<PostResponseDto> getPostList(Pageable pageable);
    PostResponseDto getPost(long id);
    void postPost(String userId, PostRequestDto postRequestDto);
    void putPost(long id, PostRequestDto postRequestDto);
    void deletePost(long id);
}
