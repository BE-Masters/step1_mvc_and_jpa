package com.example.be_study.service.user.dto;

import com.example.be_study.service.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSignUpResponse {

    private Long userId;

    private String userNickName;

    private String accessToken;

    @Builder
    public UserSignUpResponse(Long userId, String userNickName, String accessToken) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.accessToken = accessToken;
    }

    public static UserSignUpResponse of(User user, String accessToken) {
        return UserSignUpResponse.builder()
                .userId(user.getId())
                .userNickName(user.getUserNickName())
                .accessToken(accessToken)
                .build();
    }
}
