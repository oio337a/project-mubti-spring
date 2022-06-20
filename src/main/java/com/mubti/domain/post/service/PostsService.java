package com.mubti.domain.post.service;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Page<Posts> findALl(Pageable pageable){
        return postsRepository.findAll(pageable);
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
