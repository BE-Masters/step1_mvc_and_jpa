package com.example.be_study.common.error;

import org.springframework.http.HttpStatusCode;

public abstract class HouseException extends RuntimeException {

    private HttpStatusCode code;
    private String errorMessage;

    public HouseException(HttpStatusCode code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public HouseException(HttpStatusCode code, String errorMessage, Exception e) {
        this.code = code;
        this.errorMessage = errorMessage;
        e.printStackTrace();
    }
}
