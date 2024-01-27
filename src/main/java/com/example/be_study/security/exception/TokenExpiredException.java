package com.example.be_study.security.exception;

import com.example.be_study.common.error.HouseException;
import com.example.be_study.service.user.enums.UserResponseMessage;
import org.springframework.http.HttpStatusCode;

public class TokenExpiredException extends HouseException {

    public TokenExpiredException(UserResponseMessage userResponseMessage) {
        super(HttpStatusCode.valueOf(400), userResponseMessage.getResponseMessage());
    }

}
