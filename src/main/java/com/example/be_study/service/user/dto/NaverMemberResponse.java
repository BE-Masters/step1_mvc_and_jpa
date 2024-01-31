package com.example.be_study.service.user.dto;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.enums.ProviderType;
import com.example.be_study.service.user.enums.UserType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record NaverMemberResponse(
        Long id,
        boolean hasSignedUp,
        LocalDateTime connectedAt,
        NaverAccount naverAccount
) {

    public User toDomain() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return User.builder()
                .providerType(ProviderType.NAVER)
                .userLastLoginDate(currentDateTime)
                .providerKey(naverAccount.email)
                .userEmail(naverAccount.email)
                .userNickName(naverAccount.profile.nickname)
                .userProfile(naverAccount.profile.profileImageUrl)
                .dormancy(false)
                .userType(UserType.BASIC_USER)
                .build();
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record NaverAccount(
            boolean profileNeedsAgreement,
            boolean profileNicknameNeedsAgreement,
            boolean profileImageNeedsAgreement,
            Profile profile,
            boolean nameNeedsAgreement,
            String name,
            boolean emailNeedsAgreement,
            boolean isEmailValid,
            boolean isEmailVerified,
            String email,
            boolean ageRangeNeedsAgreement,
            String ageRange,
            boolean birthyearNeedsAgreement,
            String birthyear,
            boolean birthdayNeedsAgreement,
            String birthday,
            String birthdayType,
            boolean genderNeedsAgreement,
            String gender,
            boolean phoneNumberNeedsAgreement,
            String phoneNumber,
            LocalDateTime ciAuthenticatedAt
    ) {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Profile(
            String nickname,
            String thumbnailImageUrl,
            String profileImageUrl,
            boolean isDefaultImage
    ) {
    }
}
