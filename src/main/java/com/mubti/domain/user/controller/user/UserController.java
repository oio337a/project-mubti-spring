package com.mubti.domain.user.controller.user;

import com.mubti.domain.user.entity.User;
import com.mubti.domain.user.repository.UserRepository;
import com.mubti.domain.user.service.UserService;
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
    private final UserRepository userRepository;


    @GetMapping
    public ResponseEntity getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserResponseDto loginUser = userService.getUser(principal.getUsername());

        return new ResponseEntity(loginUser, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity putUser(@RequestBody UserRequestDto userRequestDto) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loginUser = userRepository.findByUserId(principal.getUsername());

        loginUser.updateUserInfo(userRequestDto);

        UserResponseDto savedUser = userService.modifyUser(loginUser);

        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/alias/{id}/check")
    public ResponseEntity checkUserAlias(@PathVariable("id") String userAlias) {
        ResponseEntity responseEntity;

        User user = userRepository.findByUserAlias(userAlias);
        if (user != null)
            responseEntity = new ResponseEntity(HttpStatus.CONFLICT);
        else
            responseEntity = new ResponseEntity(HttpStatus.OK);

        return responseEntity;
    }

}
