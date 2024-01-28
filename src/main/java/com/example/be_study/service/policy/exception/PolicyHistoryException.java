package com.example.be_study.service.policy.exception;

import com.example.be_study.common.error.HouseException;
import org.springframework.http.HttpStatusCode;

public class PolicyHistoryException extends HouseException {

    public PolicyHistoryException(HttpStatusCode code, String errorMessage) {
        super(code, errorMessage);
    }
}
