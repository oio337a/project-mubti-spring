package com.mubti.domain.post.service.impl;

import com.mubti.domain.post.entity.Post;
import com.mubti.domain.post.entity.CategoryType;
import com.mubti.domain.post.entity.SearchType;
import com.mubti.domain.post.entity.Vote;
import com.mubti.domain.post.repository.PostRepository;
import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.post.dto.PostResponseDto;
import com.mubti.domain.post.repository.VoteRepository;
import com.mubti.domain.post.service.PostService;
import com.mubti.domain.user.entity.User;
import com.mubti.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    @Override
    public Page<PostResponseDto> getPostList(Pageable pageable, String categoryType, String searchType, String keyword){
        Page<Post> postList = null;

        switch(searchType) {
            case "title" :
                postList = postRepository.findAllByPostTitle(pageable, categoryType, keyword);
                break;
            case "content" :
                postList = postRepository.findAllByPostContent(pageable, categoryType, keyword);
                break;
            case "userAlias" :
                postList = postRepository.findAllByUserAlias(pageable, categoryType, keyword);
                break;
            case "title_content" :
                postList = postRepository.findAllByPostTitleOrPostContent(pageable, categoryType, keyword);
                break;
            default :
                postList = postRepository.findAll(pageable);
        }

        return postList.map(post -> new PostResponseDto(post));
    }

    @Override
    @Transactional
    public PostResponseDto getPost(long postSeq) {
        Post post = postRepository.findById(postSeq).get();
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

        postRepository.save(postRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void putPost(long postSeq, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postSeq).get();
        post.updateTitleAndContent(postRequestDto);
    }

    @Override
    @Transactional
    public void deletePost(long postSeq) {
        postRepository.deleteById(postSeq);
    }

    @Override
    @Transactional
    public ResponseEntity postVote(String userId, long postSeq) {
        Vote vote = voteRepository.findByUserIdAndPostSeq(userId, postSeq);
        if (vote != null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        Post post = postRepository.findById(postSeq).get();
        post.updateVote();

        vote = Vote.builder()
                .voteSeq(null)
                .userId(userId)
                .postSeq(postSeq)
                .build();
        voteRepository.save(vote);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
