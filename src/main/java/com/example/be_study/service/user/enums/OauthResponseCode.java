package com.example.be_study.service.user.enums;

import com.example.be_study.common.response.DataResponseCode;

public enum OauthResponseCode implements DataResponseCode {
    LOGIN_SUCCESS(200, "로그인 완료"),
    OAUTH_AUTHORIZE_SUCCESS(200, "인가 코드 정상처리"),
    REDIRECT_FAILED(500, "리디렉션 실패"),

    ;

    private final int responseStatus;
    private final String responseMessage;

    OauthResponseCode(int responseStatus, String responseMessage){
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }
    @Override
    public int getResponseStatus() {
        return responseStatus;
    }

    @Override
    public String getResponseMessage() {
        return responseMessage;
    }
}
