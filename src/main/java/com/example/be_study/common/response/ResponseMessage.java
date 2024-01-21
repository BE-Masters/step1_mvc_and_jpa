package com.example.be_study.common.response;

import lombok.Getter;

@Getter
public enum ResponseMessage implements DataResponseCode {

    SUCCESS_MESSAGE(200, "API Call Successful"),
    TOKEN_EXPIRED_MESSAGE(200, "Token Expired Error");

    private final int responseStatus;

    private final String responseMessage;

    ResponseMessage(int responseStatus, String responseMessage) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }

}
