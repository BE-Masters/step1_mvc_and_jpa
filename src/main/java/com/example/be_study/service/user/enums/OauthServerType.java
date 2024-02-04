package com.example.be_study.service.user.enums;

import java.util.Locale;

public enum OauthServerType {
    KAKAO("카카오"), NAVER("네이버");
    
    private final String description;

    OauthServerType(String description) {
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public static OauthServerType fromName(String type) {
        return OauthServerType.valueOf(type.toUpperCase(Locale.ENGLISH));
    }
}
