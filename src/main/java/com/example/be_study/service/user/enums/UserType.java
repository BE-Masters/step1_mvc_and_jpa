package com.example.be_study.service.user.enums;

public enum UserType {

    ADMIN("관리자"),
    BASIC_USER("유저");

    private String description;

    UserType(String description) {
        this.description = description;
    }

}
