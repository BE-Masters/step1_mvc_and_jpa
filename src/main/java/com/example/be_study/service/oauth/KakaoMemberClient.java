package com.example.be_study.service.oauth;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.dto.KakaoMemberResponse;
import com.example.be_study.service.user.enums.OauthServerType;
import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthRegistrationConfig kakaoOauthRegistrationConfig;
    private final KakaoOauthProviderConfig kakaoOauthProviderConfig;
    private RestTemplate restTemplate;

    public KakaoMemberClient(KakaoApiClient kakaoApiClient, KakaoOauthRegistrationConfig kakaoOauthRegistrationConfig, KakaoOauthProviderConfig kakaoOauthProviderConfig, RestTemplate restTemplate) {
        this.kakaoApiClient = kakaoApiClient;
        this.kakaoOauthRegistrationConfig = kakaoOauthRegistrationConfig;
        this.kakaoOauthProviderConfig = kakaoOauthProviderConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public User requestAuthorizationAndAccessToken(String authCode) {
        MultiValueMap<String, String>  requestContent = tokenRequestParams(authCode);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestContent, headers);

        String result = restTemplate. postForObject(kakaoOauthProviderConfig.tokenUri(),entity, String.class);

        KakaoToken tokenInfo = new Gson().fromJson(result, KakaoToken.class);
        KakaoMemberResponse kakaoMemberResponse = kakaoApiClient.fetchMember("Bearer " + tokenInfo.getAccessToken());
        return kakaoMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthRegistrationConfig.clientId());
        params.add("redirect_uri", kakaoOauthRegistrationConfig.redirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthRegistrationConfig.clientSecret());
        return params;
    }
}
