package com.example.be_study.service.user.exception;

import com.example.be_study.common.error.HouseException;
import com.example.be_study.service.user.enums.UserResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class NotFoundUserException extends HouseException {

    public NotFoundUserException(UserResponseMessage userResponseMessage) {
        super(HttpStatusCode.valueOf(400), userResponseMessage.getResponseMessage());
    }
}
