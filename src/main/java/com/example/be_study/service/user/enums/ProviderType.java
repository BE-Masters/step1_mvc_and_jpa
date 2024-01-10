package com.example.be_study.service.user.enums;

public enum ProviderType {
    KAKAO("카카오"),
    NAVER("네이버"),
    FACEBOOK("페이스북"),
    ORIGIN("자체회원가입");

    private String description;

    ProviderType(String description) {
        this.description = description;
    }


}
