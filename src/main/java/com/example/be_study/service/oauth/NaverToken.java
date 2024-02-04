package com.example.be_study.service.oauth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class NaverToken {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private Integer refreshTokenExpiresIn;

    private String scope;
}
