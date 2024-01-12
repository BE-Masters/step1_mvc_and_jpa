package com.example.be_study.common.error;

import com.example.be_study.common.error.exception.BadRequestApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  올바르지 않은 요청
     *  HttpStatus 400
     */
    @ExceptionHandler(BadRequestApiException.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse badRequestApiException(BadRequestApiException e) {
        log.info("Error : ", e);
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
