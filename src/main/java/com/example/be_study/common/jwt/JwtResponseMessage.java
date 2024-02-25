package com.example.be_study.common.jwt;

import com.example.be_study.common.response.DataResponseCode;
import lombok.Getter;

@Getter
public enum JwtResponseMessage implements DataResponseCode {

    TOKEN_EXPIRED_MESSAGE(200, "토큰이 만료되었습니다."),

    TOKEN_PERMISSION_ERROR_MESSAGE(200, "권한이 없는 사용자입니다.");

    private final int responseStatus;

    private final String responseMessage;

    JwtResponseMessage(int responseStatus, String responseMessage) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }
}
