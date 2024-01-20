package com.example.be_study.common.error.exception;

import com.example.be_study.common.response.ResponseMessage;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() { super(ResponseMessage.TOKEN_EXPIRED_MESSAGE); }

}
