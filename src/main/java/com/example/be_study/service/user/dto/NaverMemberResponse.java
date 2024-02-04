package com.example.be_study.service.user.dto;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.ProviderType;
import com.example.be_study.service.user.enums.UserType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record NaverMemberResponse(
        String resultcode,
        String message,
        Response response

) {

    public User toDomain() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return User.builder()
                .providerType(ProviderType.NAVER)
                .userLastLoginDate(currentDateTime)
                .providerKey(response.email)
                .userEmail(response.email)
                .userNickName(response.nickname)
                .userProfile(response.profileImage)
                .dormancy(false)
                .userType(UserType.BASIC_USER)
                .build();
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Response(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profileImage,
            String birthyear,
            String mobile
    ) {
    }

}
