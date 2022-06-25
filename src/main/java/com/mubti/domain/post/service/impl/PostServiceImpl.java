package com.mubti.domain.post.service.impl;

import com.mubti.domain.post.entity.Post;
import com.mubti.domain.post.entity.CategoryType;
import com.mubti.domain.post.repository.PostRepository;
import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.post.dto.PostResponseDto;
import com.mubti.domain.post.service.PostService;
import com.mubti.domain.user.entity.User;
import com.mubti.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postsRepository;
    private final UserRepository userRepository;

    @Override
    public Page<PostResponseDto> getPostList(Pageable pageable){
        Page<Post> postList = postsRepository.findAll(pageable);

        return postList.map(post -> new PostResponseDto(post));
    }

    @Override
    public Page<PostResponseDto> getPostListByCategory(Pageable pageable, CategoryType categoryType) {
        Page<Post> postList = postsRepository.findAllByCategoryTypeContaining(pageable, categoryType);

        return postList.map(post -> new PostResponseDto(post));
    }

/*    public Page<Post> findAllByTargetAndKeyword(Pageable pageable, String target, String keyword) {
        Page<Post> posts;

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

    @Override
    @Transactional
    public PostResponseDto getPost(long id) {
        Post post = postsRepository.findById(id).get();
        if (post != null) {
            post.updateView();
        }

        return new PostResponseDto(post);
    }

    @Override
    @Transactional
    public void postPost(String userId, PostRequestDto postRequestDto) {
        User user = userRepository.findByUserId(userId);
        postRequestDto.setUser(user);
        Post post = postRequestDto.toEntity();

        postsRepository.save(post);
    }

    @Override
    @Transactional
    public void putPost(long id, PostRequestDto postRequestDto) {
        Post post = postsRepository.findById(id).get();
        post.updateTitleAndContent(postRequestDto);
    }

    @Override
    @Transactional
    public void deletePost(long id) {
        postsRepository.deleteById(id);
    }
}
