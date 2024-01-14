package com.example.be_study.service.policy.enums;

import com.example.be_study.common.response.DataResponse;
import com.example.be_study.common.response.DataResponseCode;
import lombok.Getter;

public enum PolicyAgreeResponseCode implements DataResponseCode {
    SUCCESS(200,"정상 처리 완료"),
    ALREADY_AGREED(200,"이미 동의를 마쳤습니다."),
    NEED_TO_AGREED(200,"새롭게 동의할 내역이 있습니다.");

    private final int responseStatus;
    private final String responseMessage;

    PolicyAgreeResponseCode(int responseStatus, String responseMessage) {
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
