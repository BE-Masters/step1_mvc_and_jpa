package com.example.be_study.service.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminUserInfoDto {

    private Long userId;

    private short age;

    public AdminUserInfoDto(Long userId, short age) {
        this.userId = userId;
        this.age = age;
    }
}
