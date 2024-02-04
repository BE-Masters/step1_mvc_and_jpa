package com.example.be_study.service.oauth;

import com.example.be_study.service.user.enums.OauthServerType;
import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class NaverAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final NaverOauthRegistrationConfig naverOauthRegistrationConfig;
    private final NaverOauthProviderConfig naverOauthProviderConfig;

    @Builder
    public NaverAuthCodeRequestUrlProvider(NaverOauthRegistrationConfig naverOauthRegistrationConfig, NaverOauthProviderConfig naverOauthProviderConfig) {
        this.naverOauthRegistrationConfig = naverOauthRegistrationConfig;
        this.naverOauthProviderConfig = naverOauthProviderConfig;
    }


    @Override
    public OauthServerType supportServer() {
        return OauthServerType.NAVER;
    }

    @Override
    public String provide() {
        String state = "";
        try {
            state = URLEncoder.encode(naverOauthRegistrationConfig.redirectUri(), "UTF-8");
        } catch (UnsupportedEncodingException e){
            throw new RuntimeException("인코딩에러");
        }
        return UriComponentsBuilder
                .fromUriString(naverOauthProviderConfig.authorizationUri())
                .queryParam("response_type", "code")
                .queryParam("client_id", String.join("=",naverOauthRegistrationConfig.clientId()))
                .queryParam("redirect_uri", String.join("=",naverOauthRegistrationConfig.redirectUri()))
                .queryParam("state", state)
                .toUriString();
    }
}
