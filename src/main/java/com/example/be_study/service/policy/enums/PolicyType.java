package com.example.be_study.service.policy.enums;

public enum PolicyType {
    IS_OVER_FOURTEEN("만 14세 이상"),
    TERMS_OF_SERVICE("이용약관"),
    PERSONAL_INFO_PROCESS("개인정보 수집 및 이용동의"),
    PERSONAL_INFO_MARKETING("개인정보 마케팅 활용 동의"),
    EVENT_MAIL_OR_SMS("이벤트, 쿠폰, 특가 알림 메일 및 SMS 등 수신");

    private String description;

    PolicyType(String description) {
        this.description = description;
    }
}
