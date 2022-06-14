package com.mubti.domain.user.controller.user;

import com.mubti.domain.user.entity.user.User;
import com.mubti.global.common.response.ApiResponse;
import com.mubti.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }

    @PutMapping
    public ApiResponse putUser(@RequestBody User changedUser) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());
        user.update(changedUser);
        User savedUser = userService.save(user);

        return ApiResponse.created("savedUser", savedUser);
    }
/*
    @GetMapping("/aliasCheck/{alias}")
    public ApiResponse aliasCheck(@PathVariable("alias") String userAlias) {


    }*/

}
