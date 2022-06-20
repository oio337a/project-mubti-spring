package com.mubti.domain.user.service.dto;

import com.mubti.domain.user.entity.user.User;
import lombok.Data;

@Data
public class UserRequestDto {
    private String userAlias;
    private String mbtiType;

    public User toEntity(){
        return User.builder()
                .userAlias(userAlias)
                .mbtiType(mbtiType)
                .build();
    }
}
