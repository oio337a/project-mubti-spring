package com.mbti.oauthlogin.api.repository;

import com.mbti.oauthlogin.api.entity.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
