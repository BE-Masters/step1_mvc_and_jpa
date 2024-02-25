package com.example.be_study.service.policy.enums;

import com.example.be_study.common.response.DataResponseCode;

public enum PolicyHistoryResponseCode implements DataResponseCode {
    SUCCESS(200,"정상 처리 완료");

    private final int responseStatus;
    private final String responseMessage;

    PolicyHistoryResponseCode(int responseStatus, String responseMessage) {
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
