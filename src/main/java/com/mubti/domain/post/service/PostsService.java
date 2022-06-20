package com.mubti.domain.post.service;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.repository.PostsRepository;
import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Page<PostResponseDto> getPostList(Pageable pageable){
        Page<Posts> postList = postsRepository.findAll(pageable);
        return postList.map(posts -> new PostResponseDto(posts));
    }

   /* public Page<Posts> findAllByPostCategory(Pageable pageable, String category) {
        return postsRepository.findAllByPostCategory(pageable, category);
    }*/

/*    public Page<Posts> findAllByTargetAndKeyword(Pageable pageable, String target, String keyword) {
        Page<Posts> posts;

        switch(target) {
            case "title_content" :
                posts = postsRepository.findAllByPostTitleAndPostContentContaining(pageable, keyword);
                break;
            case "title" :
                posts = postsRepository.findAllByPostTitleContaining(pageable, keyword);
                break;
            case "content" :
                posts = postsRepository.findALlByPostContentContaining(pageable, keyword);
                break;
            case "alias" :
                posts = postsRepository.findAllByUserAliasContaining(pageable, keyword);
                break;
            case "" :
                posts = postsRepository.findAll(pageable);
                break;
            default :
                posts = postsRepository.findAllByPostTitleAndPostContentContaining(pageable, keyword);
        }
        return posts;
    }*/

    public PostResponseDto getPost(long id) {
        Posts post = postsRepository.findById(id).get();
        if (post != null) {
            postsRepository.updateView(id);
        }

        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto registerPost(PostRequestDto postRequestDto) {
        Posts post = postRequestDto.toEntity();
        Posts savedPost = postsRepository.save(post);

        return new PostResponseDto(savedPost);
    }

    @Transactional
    public PostResponseDto modifyPost(Posts post) {
        Posts savedPost = postsRepository.save(post);

        return new PostResponseDto(savedPost);
    }

    @Transactional
    public void deleteById(long id) {
        postsRepository.deleteById(id);
    }
}
