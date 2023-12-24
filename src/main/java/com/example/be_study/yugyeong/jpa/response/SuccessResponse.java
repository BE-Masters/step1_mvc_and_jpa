package com.example.be_study.yugyeong.jpa.response;

public class SuccessResponse {

    public int code;

    public String codeMsg;

    public SuccessResponse() {
        this.code = 200;
        this.codeMsg = "API 호출 성공";
    }

}
