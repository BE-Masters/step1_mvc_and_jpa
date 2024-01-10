package com.example.be_study.service.policy.enums;

public enum SignUpPolicyType implements PolicyType {
    REQUIRED_IS_OVER_FOURTEEN("만 14세 이상(필수)"),
    REQUIRED_TERMS_OF_SERVICE("이용약관(필수)"),
    REQUIRED_PERSONAL_INFO_PROCESS("개인정보 수집 및 이용동의(필수)"),
    OPTION_PERSONAL_INFO_MARKETING("개인정보 마케팅 활용 동의(선택)"),
    OPTION_EVENT_MAIL_OR_SMS("이벤트, 쿠폰, 특가 알림 메일 및 SMS 등 수신(선택)");

    private String description;

    SignUpPolicyType(String description) {
        this.description = description;
    }
}
