package com.example.be_study.service.user.enums;

import com.example.be_study.common.response.DataResponseCode;
import lombok.Getter;

@Getter
public enum UserSignUpResponseCode implements DataResponseCode {

    SUCCESS(200,"정상 처리 완료"),

    ALREADY_EXIST_KAKAO_EMAIL(200, "이미 카카오톡 간편가입으로 가입된 이메일입니다. '카카오톡' 버튼을 눌러 로그인해주세요."),

    ALREADY_EXIST_NAVER_EMAIL(200, "이미 네이버 간편가입으로 가입된 이메일입니다. '네이버' 버튼을 눌러 로그인해주세요."),

    ALREADY_EXIST_FACEBOOK_EMAIL(200, "이미 페이스북 간편가입으로 가입된 이메일입니다. '페이스북' 버튼을 눌러 로그인해주세요."),

    ALREADY_EXIST_ORIGIN_EMAIL(200, "이미 가입한 이메일입니다. '이메일 로그인'으로 로그인해주세요."),

    ALREADY_EXIST_NICKNAME(200, "사용 중인 별명입니다."),

    INVALID_EMAIL_FORMAT(200, "이메일 형식이 올바르지 않습니다."),

    REQUIRED_FIELD(200, "필수 값입니다."),

    MAIL_SEND_FAILED(200, "메일 전송에 실패했습니다."),

    INVALID_AUTH_MAIL_CODE(200, "인증 코드가 올바르지 않습니다."),

    EXPIRED_AUTH_MAIL_CODE(200, "인증 코드가 만료되었습니다.");

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
