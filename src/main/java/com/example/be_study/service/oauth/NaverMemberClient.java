package com.example.be_study.service.oauth;

import com.example.be_study.service.user.domain.User;
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

    private final NaverApiClient naverApiClient;
    private final NaverOauthConfig naverOauthConfig;
    private RestTemplate restTemplate;

    public NaverMemberClient(NaverApiClient naverApiClient, NaverOauthConfig naverOauthConfig, RestTemplate restTemplate) {
        this.naverApiClient = naverApiClient;
        this.naverOauthConfig = naverOauthConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.NAVER;
    }

    @Override
    public User fetch(String authCode) {
        MultiValueMap<String, String>  requestContent = tokenRequestParams(authCode);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestContent, headers);

        String result = restTemplate.postForObject("https://kauth.naver.com/oauth/token",entity, String.class);

        NaverToken tokenInfo = new Gson().fromJson(result, NaverToken.class);
        NaverMemberResponse naverMemberResponse = naverApiClient.fetchMember("Bearer " + tokenInfo.getAccessToken());
        return naverMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", naverOauthConfig.clientId());
        params.add("redirect_uri", naverOauthConfig.redirectUri());
        params.add("code", authCode);
        params.add("client_secret", naverOauthConfig.clientSecret());
        return params;
    }
}
