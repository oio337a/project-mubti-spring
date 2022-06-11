package com.mubti.domain.post.controller;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.service.PostsService;
import com.mubti.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostsController {

    private final PostsService postsService;

    @GetMapping
    public ApiResponse getAllPosts() {
        List<Posts> posts = postsService.getAllPosts();

        return ApiResponse.success("posts", posts);
    }

    @GetMapping("/{id}")
    public ApiResponse getPost(@PathVariable("id") long id) {
        Posts post = postsService.getPost(id);

        return ApiResponse.success("post", post);
    }
}
