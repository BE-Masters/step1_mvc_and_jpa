package com.example.be_study.common.response;

public class DataResponse<T> extends SuccessResponse {

    public T response;

    public DataResponse(int status, String message, T response) {
        super(status, message);
        this.response = response;
    }
}
