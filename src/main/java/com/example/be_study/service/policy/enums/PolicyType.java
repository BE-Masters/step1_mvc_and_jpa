package com.example.be_study.service.policy.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PolicyType {
    REQUIRED_IS_OVER_FOURTEEN("만 14세 이상(필수)"),
    REQUIRED_TERMS_OF_SERVICE("이용약관(필수)"),
    REQUIRED_PERSONAL_INFO_PROCESS("개인정보 수집 및 이용동의(필수)"),
    OPTION_PERSONAL_INFO_MARKETING("개인정보 마케팅 활용 동의(선택)"),
    OPTION_EVENT_MAIL_OR_SMS("이벤트, 쿠폰, 특가 알림 메일 및 SMS 등 수신(선택)");

    private String description;

    public static final List<PolicyType> SIGN_REQUIRED_LIST = Arrays.asList(REQUIRED_IS_OVER_FOURTEEN, REQUIRED_TERMS_OF_SERVICE, REQUIRED_PERSONAL_INFO_PROCESS);
    public static final List<PolicyType> SIGN_OPTION_LIST = Arrays.asList(OPTION_PERSONAL_INFO_MARKETING, OPTION_EVENT_MAIL_OR_SMS);
    public static final List<PolicyType> SIGN_ALL_LIST = Stream.concat(SIGN_REQUIRED_LIST.stream(), SIGN_OPTION_LIST.stream())
            .collect(Collectors.toList());

    PolicyType(String description) {
        this.description = description;
    }
}
