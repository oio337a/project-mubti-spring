package com.mubti.domain.post.service;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.repository.PostsRepository;
import com.mubti.domain.post.service.dto.PostRequestDto;
import com.mubti.domain.post.service.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    public Posts findById(long id){
        return postsRepository.findById(id).get();
    }

    @Transactional
    public Posts save(Posts post) {
        return postsRepository.save(post);
    }

    @Transactional
    public void deleteById(long id) {
        postsRepository.deleteById(id);
    }

    @Transactional
    public void updateView(long id) {
        postsRepository.updateView(id);
    }
}
