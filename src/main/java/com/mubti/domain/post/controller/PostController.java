package com.mubti.domain.post.controller;

import com.mubti.domain.post.service.impl.PostServiceImpl;
import com.mubti.domain.post.dto.PostRequestDto;
import com.mubti.domain.post.dto.PostResponseDto;
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
public class PostController {
    private final PostServiceImpl postsService;

    @GetMapping
    public ResponseEntity getPostList(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponseDto> postList = postsService.getPostList(pageable);

        return new ResponseEntity(postList, HttpStatus.OK);
    }

/*
    @GetMapping
    public ResponseEntity getPostsByCategory(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable,
                                              @RequestParam(value = "category", required = false, defaultValue = "") String category) {
        Page<Post> posts = postsService.findAllByPostCategory(pageable, category);

        return new ResponseEntity(posts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getPostsByTargetAndKeyword(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable,
                                                     @RequestParam(value = "target", required = false, defaultValue = "") String target,
                                                     @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        Page<Post> posts = postsService.findAllByTargetAndKeyword(pageable, target, keyword);

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
        String userId = principal.getUsername();

        postsService.postPost(userId, postRequestDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity putPost(@PathVariable("id") long id, @RequestBody PostRequestDto postRequestDto) {
        postsService.putPost(id, postRequestDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable("id") long id) {
        postsService.deletePost(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
