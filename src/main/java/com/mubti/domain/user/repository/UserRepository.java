package com.mubti.domain.user.repository;

import com.mubti.domain.user.entity.User;
import com.mubti.global.common.oauth.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByUserAlias(String userAlias);
    @Query("SELECT roleType FROM User WHERE userId = :id")
    RoleType findRoleTypeByUserId(@Param("id") String userId);
}
