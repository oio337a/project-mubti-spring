package com.mubti.domain.user.service;

<<<<<<< HEAD:src/main/java/com/mubti/domain/user/service/UserService.java
import com.mubti.domain.user.entity.user.User;
import com.mubti.domain.user.repository.UserRepository;
=======
import com.mbti.oauthlogin.api.entity.user.User;
import com.mbti.oauthlogin.api.repository.UserRepository;
>>>>>>> 8e8854aa52e66c7c028dacc6012ed59f01eaa6c3:src/main/java/com/mbti/oauthlogin/api/service/UserService.java
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
