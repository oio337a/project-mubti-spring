package com.mubti.domain.post.controller;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.service.PostsService;
import com.mubti.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @GetMapping
    public ApiResponse getAllPosts() {
        List<Posts> posts = postsService.getAllPosts();

        return ApiResponse.success("posts", posts);
    }
}
