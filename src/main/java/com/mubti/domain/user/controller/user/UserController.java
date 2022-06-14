package com.mubti.domain.user.controller.user;

import com.mubti.domain.user.entity.user.User;
import com.mubti.global.common.response.ApiResponse;
import com.mubti.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserId(principal.getUsername());

        return ApiResponse.success("user", user);
    }

    @PutMapping
    public ApiResponse putUser(@RequestBody User changedUser) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUserId(principal.getUsername());
        user.update(changedUser);
        User savedUser = userService.save(user);

        return ApiResponse.created("savedUser", savedUser);
    }

    @GetMapping("/alias/{alias}")
    public ResponseEntity checkUserAlias(@PathVariable("alias") String userAlias) {
        ResponseEntity responseEntity;

        User user = userService.findByUserAlias(userAlias);
        if (user != null)
            responseEntity = new ResponseEntity(HttpStatus.CONFLICT);
        else
            responseEntity = new ResponseEntity(HttpStatus.OK);

        return responseEntity;
    }

}
