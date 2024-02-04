package com.example.be_study.service.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.naver")
public record NaverOauthRegistrationConfig(

    String clientId,
    String clientSecret,
    String redirectUri,
    String[] scope
) {
}

