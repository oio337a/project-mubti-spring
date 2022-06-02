package com.mubti.global.common.oauth.service;

<<<<<<< HEAD:src/main/java/com/mubti/global/common/oauth/service/CustomOAuth2UserService.java
import com.mubti.domain.user.entity.user.User;
import com.mubti.domain.user.repository.UserRepository;
import com.mubti.global.common.oauth.entity.ProviderType;
import com.mubti.global.common.oauth.entity.RoleType;
import com.mubti.global.common.oauth.entity.UserPrincipal;
import com.mubti.global.common.oauth.info.OAuth2UserInfo;
import com.mubti.global.common.oauth.info.OAuth2UserInfoFactory;
import com.mubti.global.common.oauth.exception.OAuthProviderMissMatchException;
=======
import com.mbti.oauthlogin.api.entity.user.User;
import com.mbti.oauthlogin.api.repository.UserRepository;
import com.mbti.oauthlogin.oauth.entity.ProviderType;
import com.mbti.oauthlogin.oauth.entity.RoleType;
import com.mbti.oauthlogin.oauth.entity.UserPrincipal;
import com.mbti.oauthlogin.oauth.exception.OAuthProviderMissMatchException;
import com.mbti.oauthlogin.oauth.info.OAuth2UserInfo;
import com.mbti.oauthlogin.oauth.info.OAuth2UserInfoFactory;
>>>>>>> 8e8854aa52e66c7c028dacc6012ed59f01eaa6c3:src/main/java/com/mbti/oauthlogin/oauth/service/CustomOAuth2UserService.java
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        User savedUser = userRepository.findByUserId(userInfo.getId());

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                        " account. Please use your " + savedUser.getProviderType() + " account to login."
                );
            }
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(
                userInfo.getId(),
                providerType,
                RoleType.USER,
                now,
                now
        );

        return userRepository.saveAndFlush(user);
    }
}
