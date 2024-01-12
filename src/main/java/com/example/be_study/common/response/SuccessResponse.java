package com.example.be_study.common.response;

public class SuccessResponse {

    public int status;

    public String message;

    public SuccessResponse() {
        this.status = 200;
        this.message = ResponseMessage.SUCCESS_MESSAGE;
    }

    public SuccessResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
