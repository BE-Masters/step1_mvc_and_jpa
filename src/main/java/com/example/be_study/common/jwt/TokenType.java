package com.example.be_study.common.jwt;

import lombok.Getter;

@Getter
public enum TokenType {

    AccessToken("Access Token", 60 * 60 * 1000L), // 1시간

    RefreshToken("Refresh Token", 365 * 24 * 60 * 60 * 1000L); // 1년

    private final String description;

    private final long expirationTime;

    TokenType(String description, long expirationTime) {
        this.description = description;
        this.expirationTime = expirationTime;
    }
}
