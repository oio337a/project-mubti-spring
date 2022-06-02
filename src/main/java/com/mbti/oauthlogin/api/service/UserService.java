package com.mbti.oauthlogin.api.service;

import com.mbti.oauthlogin.api.entity.user.User;
import com.mbti.oauthlogin.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
