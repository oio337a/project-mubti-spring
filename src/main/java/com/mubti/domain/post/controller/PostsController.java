package com.mubti.domain.post.controller;

import com.mubti.domain.post.entity.Posts;
import com.mubti.domain.post.service.PostsService;
import com.mubti.domain.user.service.UserService;
import com.mubti.global.common.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.mubti.global.common.response.ApiResponse;
import com.mubti.global.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @GetMapping
    public ApiResponse getAllPosts(HttpServletRequest request) {
        List<Posts> posts = postsService.getAllPosts();

        return ApiResponse.success("posts", posts);
    }

    @GetMapping("/{id}")
    public ApiResponse getPost(@PathVariable("id") long id) {
        postsService.updateView(id);
        Posts post = postsService.getPost(id);

        return ApiResponse.success("post", post);
    }

    @PostMapping
    public ApiResponse postPost(@RequestBody Posts post) {
        Posts savedPost = postsService.save(post);

        return ApiResponse.created("savedPost", savedPost);
    }

    @PutMapping("/{id}")
    public ApiResponse putPost(@PathVariable("id") long id, @RequestBody Posts changedPost) {
        Posts post = postsService.getPost(id);
        post.update(changedPost);
        Posts savedPost = postsService.save(post);

        return ApiResponse.created("savedPost", savedPost);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletePost(@PathVariable("id") long id) {
        postsService.deleteById(id);

        return ApiResponse.deleted();
    }

}
