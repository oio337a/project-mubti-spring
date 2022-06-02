package com.mubti.global.common.oauth.info;

import com.mubti.global.common.oauth.entity.ProviderType;
import com.mubti.global.common.oauth.info.impl.GoogleOAuth2UserInfo;
import com.mubti.global.common.oauth.info.impl.KakaoOAuth2UserInfo;
import com.mubti.global.common.oauth.info.impl.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
