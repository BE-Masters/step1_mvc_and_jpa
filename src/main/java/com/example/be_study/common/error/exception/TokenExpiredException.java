package com.example.be_study.common.error.exception;

import com.example.be_study.service.user.enums.UserResponseMessage;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() { super(UserResponseMessage.TOKEN_EXPIRED_MESSAGE.getResponseMessage()); }

}
