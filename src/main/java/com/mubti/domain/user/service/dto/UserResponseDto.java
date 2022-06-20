package com.mubti.domain.user.service.dto;

import com.mubti.domain.user.entity.User;
import com.mubti.global.common.oauth.entity.ProviderType;
import lombok.Data;

@Data
public class UserResponseDto {
    private String userAlias;
    private String mbtiType;
    private ProviderType providerType;

    public UserResponseDto(User user){
        this.userAlias = user.getUserAlias();
        this.mbtiType = user.getMbtiType();
        this.providerType = user.getProviderType();
    }
}
