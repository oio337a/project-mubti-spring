package com.mubti.domain.post.service;

import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.post.dto.PostResponseDto;
import com.mubti.domain.post.entity.CategoryType;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PostService {
    Page<PostResponseDto> getPostList(Pageable pageable);
    Page<PostResponseDto> getPostListByCategory(Pageable pageable, CategoryType categoryType);
    PostResponseDto getPost(long id);
    void postPost(String userId, PostRequestDto postRequestDto);
    void putPost(long id, PostRequestDto postRequestDto);
    void deletePost(long id);
    ResponseEntity postVote(String userId, long postSeq);
}
