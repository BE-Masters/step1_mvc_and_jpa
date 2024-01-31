package com.example.be_study.service.oauth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class NaverToken {

    @SerializedName("access_token")
    String accessToken;

    @SerializedName("refresh_token")
    String refreshToken;

    @SerializedName("token_type")
    String tokenType;

    @SerializedName("expires_in")
    Integer refreshTokenExpiresIn;

    String scope;
}
