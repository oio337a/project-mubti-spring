package com.mubti.domain.post.controller;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.repository.PostsRepository;
import com.mubti.domain.post.service.PostsService;
import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.post.dto.PostResponseDto;
import com.mubti.domain.user.entity.User;
import com.mubti.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {
    private final PostsService postsService;
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity getPostList(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponseDto> postList = postsService.getPostList(pageable);

        return new ResponseEntity(postList, HttpStatus.OK);
    }

/*
    @GetMapping
    public ResponseEntity getPostsByCategory(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable,
                                              @RequestParam(value = "category", required = false, defaultValue = "") String category) {
        Page<Posts> posts = postsService.findAllByPostCategory(pageable, category);

        return new ResponseEntity(posts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getPostsByTargetAndKeyword(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable,
                                                     @RequestParam(value = "target", required = false, defaultValue = "") String target,
                                                     @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        Page<Posts> posts = postsService.findAllByTargetAndKeyword(pageable, target, keyword);

        return new ResponseEntity(posts, HttpStatus.OK);
    }
*/

    @GetMapping("/{id}")
    public ResponseEntity getPost(@PathVariable("id") long id) {
        PostResponseDto post = postsService.getPost(id);

        return new ResponseEntity(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postPost(@RequestBody PostRequestDto postRequestDto) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loginUser = userRepository.findByUserId(principal.getUsername());

        postRequestDto.setUser(loginUser);

        PostResponseDto savedPost = postsService.registerPost(postRequestDto);

        return new ResponseEntity(savedPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity putPost(@PathVariable("id") long id, @RequestBody PostRequestDto postRequestDto) {
        Posts post = postsRepository.findById(id).get();
        post.updateTitleAndContent(postRequestDto);

        PostResponseDto savedPost = postsService.modifyPost(post);

        return new ResponseEntity(savedPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable("id") long id) {
        postsService.deleteById(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
