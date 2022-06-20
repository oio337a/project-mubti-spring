package com.mubti.domain.user.service;

import com.mubti.domain.user.entity.user.User;
import com.mubti.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public User findByUserAlias(String findByUserAlias) {
        return userRepository.findByUserAlias(findByUserAlias);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
