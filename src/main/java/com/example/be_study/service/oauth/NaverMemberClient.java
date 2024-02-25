package com.example.be_study.service.oauth;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.dto.KakaoMemberResponse;
import com.example.be_study.service.user.dto.NaverMemberResponse;
import com.example.be_study.service.user.enums.OauthServerType;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class NaverMemberClient implements OauthMemberClient {

    private final NaverOauthRegistrationConfig naverOauthRegistrationConfig;
    private final NaverOauthProviderConfig naverOauthProviderConfig;
    private final RestTemplate restTemplate;

    public NaverMemberClient( NaverOauthRegistrationConfig naverOauthRegistrationConfig, NaverOauthProviderConfig naverOauthProviderConfig, RestTemplate restTemplate) {
        this.naverOauthRegistrationConfig = naverOauthRegistrationConfig;
        this.naverOauthProviderConfig = naverOauthProviderConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.NAVER;
    }

    @Override
    public User requestAuthorizationAndAccessToken (String authCode) {
        MultiValueMap<String, String>  requestContent = tokenRequestParams(authCode);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestContent, headers);

        String result = restTemplate.postForObject(naverOauthProviderConfig.tokenUri(),entity, String.class);

        NaverToken tokenInfo = new Gson().fromJson(result, NaverToken.class);

        return requestUserInfo(tokenInfo.getAccessToken());
    }

    public User requestUserInfo(String AccessToken) {
        MultiValueMap<String, Object>  requestContent = new LinkedMultiValueMap<String, Object>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + AccessToken);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(requestContent, headers);

        String userInfo = restTemplate.postForObject(naverOauthProviderConfig.userInfoUri(),entity, String.class);

        NaverMemberResponse naverMemberResponse = new Gson().fromJson(userInfo, NaverMemberResponse.class);

        return naverMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", naverOauthRegistrationConfig.clientId());
        params.add("redirect_uri", naverOauthRegistrationConfig.redirectUri());
        params.add("code", authCode);
        params.add("client_secret", naverOauthRegistrationConfig.clientSecret());
        return params;
    }
}
