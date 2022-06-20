package com.mubti.domain.user.service;

import com.mubti.domain.user.dto.UserRequestDto;
import com.mubti.domain.user.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserResponseDto getUser(String userId);
    void putUser(String userId, UserRequestDto userRequestDto);
    ResponseEntity checkUserAlias(String userAlias);
}
