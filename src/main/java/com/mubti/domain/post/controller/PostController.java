package com.mubti.domain.post.controller;

import com.mubti.domain.post.service.PostService;
import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
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
public class PostController {
    private final PostService postsService;

    @GetMapping
    public ResponseEntity getPostList(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable) {

        return new ResponseEntity(postsService.getPostList(pageable), HttpStatus.OK);
    }

    @GetMapping("/{postSeq}")
    public ResponseEntity getPost(@PathVariable("postSeq") long postSeq) {
        PostResponseDto post = postsService.getPost(postSeq);

        return new ResponseEntity(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postPost(@RequestBody PostRequestDto postRequestDto) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = principal.getUsername();

        postsService.postPost(userId, postRequestDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{postSeq}")
    public ResponseEntity putPost(@PathVariable("postSeq") long postSeq, @RequestBody PostRequestDto postRequestDto) {
        postsService.putPost(postSeq, postRequestDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{postSeq}")
    public ResponseEntity deletePost(@PathVariable("postSeq") long postSeq) {
        postsService.deletePost(postSeq);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{postSeq}/vote")
    public ResponseEntity checkPostVote(@PathVariable("postSeq") long postSeq) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = principal.getUsername();

        ResponseEntity responseEntity = postsService.checkPostVote(userId, postSeq);

        return responseEntity;
    }
}
