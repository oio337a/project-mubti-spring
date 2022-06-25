package com.mubti.domain.user.service.impl;

import com.mubti.domain.user.dto.UserRequestDto;
import com.mubti.domain.user.entity.User;
import com.mubti.domain.user.repository.UserRepository;
import com.mubti.domain.user.dto.UserResponseDto;
import com.mubti.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUser(String userId) {
        User user = userRepository.findByUserId(userId);

        return new UserResponseDto(user);
    }

    @Override
    @Transactional
    public void putUser(String userId, UserRequestDto userRequestDto) {
        User user = userRepository.findByUserId(userId);
        user.updateInfo(userRequestDto);
    }

    @Override
    public ResponseEntity checkUserAlias(String userAlias) {
        User user = userRepository.findByUserAlias(userAlias);
        if (user != null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
