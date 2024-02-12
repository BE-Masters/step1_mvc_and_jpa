package com.example.be_study.service.admin.enums;

import com.example.be_study.common.response.DataResponseCode;
import lombok.Getter;

@Getter
public enum AdminResponseMessage implements DataResponseCode {

    SUCCESS(200,"정상 처리 완료");

    private final int responseStatus;

    private final String responseMessage;

    AdminResponseMessage(int responseStatus, String responseMessage) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }
}
