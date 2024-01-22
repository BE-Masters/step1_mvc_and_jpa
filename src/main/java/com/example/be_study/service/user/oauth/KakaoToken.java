package com.example.be_study.service.user.oauth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class KakaoToken {

    @SerializedName("token_type")
    String tokenType;

    @SerializedName("access_token")
    String accessToken;

    @SerializedName("id_token")
    String idToken;

    @SerializedName("expires_in")
    Integer expiresIn;

    @SerializedName("refresh_token")
    String refreshToken;

    @SerializedName("refresh_token_expires_in")
    Integer refreshTokenExpiresIn;
    String scope;
}
