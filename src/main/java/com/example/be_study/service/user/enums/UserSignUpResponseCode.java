package com.example.be_study.service.user.enums;

import com.example.be_study.common.response.DataResponseCode;
import lombok.Getter;

@Getter
public enum UserSignUpResponseCode implements DataResponseCode {

    SUCCESS(200,"정상 처리 완료"),

    ALREADY_EXIST_EMAIL(200, "이미 가입된 이메일입니다."),

    ALREADY_EXIST_NICKNAME(200, "사용 중인 별명입니다.");

    private final int responseStatus;

    private final String responseMessage;

    UserSignUpResponseCode(int responseStatus, String responseMessage) {
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
