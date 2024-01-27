package com.example.be_study.service.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.kakao")
public record KakaoOauthConfig (
    String redirectUri,
    String clientId,
    String clientSecret,
    String[] scope
) {
}
