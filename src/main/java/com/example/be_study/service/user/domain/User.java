package com.example.be_study.service.user.domain;

import com.example.be_study.service.base.AbstractBaseEntity;
import com.example.be_study.service.user.enums.ProviderType;
import com.example.be_study.service.user.enums.UserType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends AbstractBaseEntity {
    @Column(name = "user_profile", nullable = true)
    private String userProfile;

    @Column(name = "user_email", nullable = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @Column(name = "user_nickname", nullable = false)
    private String userNickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type", nullable = false)
    private ProviderType providerType;

    @Column(name = "provider_key", nullable = false)
    private String providerKey;

    @Column(name = "user_last_login_date", nullable = false)
    private String userLastLoginDate;

    @Column(name = "dormancy", nullable = false, columnDefinition = "boolean default false")
    private Boolean dormancy;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted;

    @Builder
    public User(String userProfile,
                String userEmail,
                String userPassword,
                UserType userType,
                String userNickName,
                ProviderType providerType,
                String providerKey,
                String userLastLoginDate,
                Boolean dormancy,
                Boolean deleted
    ) {
        this.userProfile = userProfile;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType = userType;
        this.userNickName = userNickName;
        this.providerType = providerType;
        this.providerKey = providerKey;
        this.userLastLoginDate = userLastLoginDate;
        this.dormancy = dormancy;
        this.deleted = deleted;
    }
}
