package com.example.be_study.service.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.client.provider.kakao")
public record KakaoOauthProviderConfig(
        String authorizationUri,
        String tokenUri,
        String userInfoUri,
        String userNameAttribute
) {
}
