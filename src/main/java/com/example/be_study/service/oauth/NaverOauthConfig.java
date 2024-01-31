package com.example.be_study.service.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.naver")
public record NaverOauthConfig(
    String redirectUri,
    String clientId,
    String clientSecret,
    String[] state
) {
}
