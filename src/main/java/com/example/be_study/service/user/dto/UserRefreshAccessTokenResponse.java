package com.example.be_study.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRefreshAccessTokenResponse {

    private String accessToken;

    @Builder
    public UserRefreshAccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public static UserRefreshAccessTokenResponse of(String accessToken) {
        return UserRefreshAccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
