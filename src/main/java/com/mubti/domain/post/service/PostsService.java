package com.mubti.domain.post.service;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public List<Posts> getAllPosts(){
        return postsRepository.findAll(Sort.by(Sort.Direction.DESC, "postSeq"));
    }

    public Posts findById(long id){
        return postsRepository.findById(id).get();
    }

    public Posts save(Posts post) {
        return postsRepository.save(post);
    }

    public void deleteById(long id) {
        postsRepository.deleteById(id);
    }

    @Transactional
    public void updateView(long id) {
        postsRepository.updateView(id);
    }
}
