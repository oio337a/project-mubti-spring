package com.mubti.domain.post.controller;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {
    private final PostsService postsService;

    @GetMapping
    public ResponseEntity getAllPosts(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Posts> posts = postsService.findALl(pageable);

        return new ResponseEntity(posts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCategoricalPosts(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable,
                                              @RequestParam(value = "category", required = false, defaultValue = "") String category) {
        Page<Posts> posts = postsService.findAllByPostCategory(pageable, category);

        return new ResponseEntity(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPost(@PathVariable("id") long id) {
        postsService.updateView(id);
        Posts post = postsService.findById(id);

        return new ResponseEntity(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postPost(@RequestBody Posts post) {
        Posts savedPost = postsService.save(post);

        return new ResponseEntity(savedPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity putPost(@PathVariable("id") long id, @RequestBody Posts changePost) {
        Posts post = postsService.findById(id);
        post.update(changePost);
        Posts savedPost = postsService.save(post);

        return new ResponseEntity(savedPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable("id") long id) {
        postsService.deleteById(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
