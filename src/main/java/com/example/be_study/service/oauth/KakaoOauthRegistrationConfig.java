package com.example.be_study.service.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.kakao")
public record KakaoOauthRegistrationConfig (
    String redirectUri,
    String clientId,
    String clientSecret,
    String[] scope
) {
}
