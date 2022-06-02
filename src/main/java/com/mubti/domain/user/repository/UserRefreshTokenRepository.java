<<<<<<< HEAD:src/main/java/com/mubti/domain/user/repository/UserRefreshTokenRepository.java
package com.mubti.domain.user.repository;
=======
package com.mbti.oauthlogin.api.repository;
>>>>>>> 8e8854aa52e66c7c028dacc6012ed59f01eaa6c3:src/main/java/com/mbti/oauthlogin/api/repository/user/UserRefreshTokenRepository.java

import com.mubti.domain.user.entity.user.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByUserId(String userId);
    UserRefreshToken findByUserIdAndRefreshToken(String userId, String refreshToken);
}
