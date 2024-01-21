package com.example.be_study.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DataResponse<T> {

    private final int status;

    private final String message;

    private T response;

    public DataResponse(DataResponseCode dataResponseCode, T response) {
        this.status = dataResponseCode.getResponseStatus();
        this.message = dataResponseCode.getResponseMessage();
        this.response = response;
    }

    public DataResponse(DataResponseCode dataResponseCode) {
        this.status = dataResponseCode.getResponseStatus();
        this.message = dataResponseCode.getResponseMessage();
    }

    public DataResponse(int status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public DataResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static DataResponse<String> of(HttpStatus httpStatus, String message) {
        return new DataResponse<>(httpStatus.value(), message, null);
    }
}
