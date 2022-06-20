package com.mubti.domain.user.controller.user;

import com.mubti.domain.user.service.UserService;
import com.mubti.domain.user.service.impl.UserServiceImpl;
import com.mubti.domain.user.dto.UserRequestDto;
import com.mubti.domain.user.dto.UserResponseDto;
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
    public ResponseEntity getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = principal.getUsername();

        UserResponseDto user = userService.getUser(userId);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity putUser(@RequestBody UserRequestDto userRequestDto) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = principal.getUsername();

        userService.putUser(userId, userRequestDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/alias/{userAlias}/check")
    public ResponseEntity checkUserAlias(@PathVariable("userAlias") String userAlias) {
        return userService.checkUserAlias(userAlias);
    }

}
