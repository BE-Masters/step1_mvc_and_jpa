package com.example.be_study.common.response;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    SUCCESS_MESSAGE(200, "API Call Successful"),
    BAD_REQUEST_MESSAGE(400, "Bad Request"),
    ALREADY_EXIST_NICKNAME(400, "사용 중인 별명입니다."),
    ALREADY_EXIST_EMAIL(400, "이미 가입된 이메일입니다."),
    TOKEN_EXPIRED_MESSAGE(400, "Token Expired Error");

    private final int responseStatus;

    private final String responseMessage;

    ResponseMessage(int responseStatus, String responseMessage) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }

}
