package com.mubti.domain.user.service;

import com.mubti.domain.user.entity.User;
import com.mubti.domain.user.repository.UserRepository;
import com.mubti.domain.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto getUser(String userId) {
        User user = userRepository.findByUserId(userId);

        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto modifyUser(User user) {
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser);
    }
}
