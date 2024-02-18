package com.example.be_study.service.user.dto;

import com.example.be_study.service.user.repository.UserPrincipal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrincipalResponse {
    private Long userId;
    private String userNickName;
    private String userEmail;

    @Builder
    public UserPrincipalResponse(Long userId, String userNickName, String userEmail) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userEmail = userEmail;
    }

    public static UserPrincipalResponse of(UserPrincipal userPrincipal){
        return UserPrincipalResponse.builder()
                .userId(userPrincipal.getUserId())
                .userNickName(userPrincipal.getUserNickName())
                .userEmail(userPrincipal.getUserEmail())
                .build();
    }
}
