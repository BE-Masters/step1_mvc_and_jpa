package com.example.be_study.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DataResponse<T> {

    public int status;

    public String message;

    public T response;

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
