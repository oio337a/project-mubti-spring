package com.mubti.domain.user.dto;

import com.mubti.domain.user.entity.User;
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
