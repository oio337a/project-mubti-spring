package com.mubti.domain.post.service;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public List<Posts> getAllPosts(){
        return postsRepository.findAll();
    }
    /*
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
*/
}
