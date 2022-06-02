package com.mbti.oauthlogin.api.controller;

import com.mbti.oauthlogin.api.entity.model.dto.ReadPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/posts/{id}")
    public String read(@PathVariable Long id, Model model) {
        ReadPost dto = postsService.findByID(id);


    }
}
