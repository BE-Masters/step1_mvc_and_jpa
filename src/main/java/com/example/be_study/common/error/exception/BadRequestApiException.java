package com.example.be_study.common.error.exception;

public class BadRequestApiException extends RuntimeException {
    public BadRequestApiException(String message) {
        super(message);
    }
}
