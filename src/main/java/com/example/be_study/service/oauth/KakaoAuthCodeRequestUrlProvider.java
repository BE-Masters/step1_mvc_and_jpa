package com.example.be_study.service.oauth;

import com.example.be_study.service.user.enums.OauthServerType;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final KakaoOauthRegistrationConfig kakaoOauthRegistrationConfig;
    private final KakaoOauthProviderConfig kakaoOauthProviderConfig;

    @Builder
    public KakaoAuthCodeRequestUrlProvider(KakaoOauthRegistrationConfig kakaoOauthRegistrationConfig, KakaoOauthProviderConfig kakaoOauthProviderConfig) {
        this.kakaoOauthRegistrationConfig = kakaoOauthRegistrationConfig;
        this.kakaoOauthProviderConfig = kakaoOauthProviderConfig;
    }

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString(kakaoOauthProviderConfig.authorizationUri())
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoOauthRegistrationConfig.clientId())
                .queryParam("redirect_uri", kakaoOauthRegistrationConfig.redirectUri())
                .queryParam("scope", String.join(",", kakaoOauthRegistrationConfig.scope()))
                .toUriString();
    }
}
