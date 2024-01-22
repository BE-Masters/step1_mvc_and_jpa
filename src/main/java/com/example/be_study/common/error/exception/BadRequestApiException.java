package com.example.be_study.common.error.exception;

import com.example.be_study.service.user.enums.UserResponseMessage;
import lombok.Getter;

@Getter
public class BadRequestApiException extends RuntimeException {

    private final UserResponseMessage userResponseMessage;

    public BadRequestApiException(UserResponseMessage userResponseMessage) {
        super(userResponseMessage.getResponseMessage());
        this.userResponseMessage = userResponseMessage;
    }
}
