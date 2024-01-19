package com.example.be_study.common.error.exception;

import com.example.be_study.common.response.ResponseMessage;
import lombok.Getter;

@Getter
public class BadRequestApiException extends RuntimeException {

    private final ResponseMessage responseMessage;

    public BadRequestApiException(ResponseMessage responseMessage) {
        super(responseMessage.getResponseMessage());
        this.responseMessage = responseMessage;
    }
}
