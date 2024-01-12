package com.example.be_study.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    public int status;

    public String message;

    public static ErrorResponse of(HttpStatus httpStatus, String message) {
        return new ErrorResponse(httpStatus.value(), message);
    }

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
