package com.example.be_study.service.s3.exception;

import com.example.be_study.common.error.HouseException;
import org.springframework.http.HttpStatusCode;

public class S3ImageUploadException extends HouseException {

    public S3ImageUploadException(HttpStatusCode code,
                                  String errorMessage) {
        super(code, errorMessage);
    }

    public S3ImageUploadException(HttpStatusCode code,
                                  String errorMessage,
                                  Exception e) {
        super(code, errorMessage, e);
    }
}
