package com.mubti.domain.post.controller;

import com.mubti.domain.post.entity.CategoryType;
import com.mubti.domain.post.entity.SearchType;
import com.mubti.domain.post.service.PostService;
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
    private final PostService postsService;

    @GetMapping
    public ResponseEntity getPostList(@PageableDefault(page = 0, size = 10, sort = "postSeq", direction = Sort.Direction.DESC) Pageable pageable,
                                      @RequestParam(value = "category_type", required = false, defaultValue = "") CategoryType categoryType,
                                      @RequestParam(value = "search_type", required = false, defaultValue = "") SearchType searchType,
                                      @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        if (categoryType != null) {
            return new ResponseEntity(postsService.getPostListByCategory(pageable, categoryType), HttpStatus.OK);
        }
        return new ResponseEntity(postsService.getPostList(pageable), HttpStatus.OK);
    }

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
