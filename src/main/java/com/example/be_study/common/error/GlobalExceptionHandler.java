package com.example.be_study.common.error;

import com.example.be_study.service.user.exception.UserBadRequestApiException;
import com.example.be_study.common.response.DataResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 올바르지 않은 요청
     * HttpStatus 400
     */
    @ExceptionHandler(UserBadRequestApiException.class)
    @ResponseStatus(HttpStatus.OK)
    public DataResponse<String> badRequestApiException(UserBadRequestApiException e) {
        log.error("Error : ", e);
        return DataResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * 업로드 용량 초과 익셉션
     * HttpStatus 413
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void maxUploadSizeExceededException(MaxUploadSizeExceededException e, HttpServletRequest request) {
        long actualFileSize = Long.parseLong(request.getHeader("Content-Length"));
        log.error("업로드 용량 초과. 업로드된 파일 크기: {} bytes", actualFileSize, e);
        throw e;
    }

    /**
     * 컨트롤러의 매개변수 검증이 실패시 발생
     * HttpStatus 413
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) throws MethodArgumentNotValidException {
        log.error("잘못된 Request 값이 존재합니다.");
        e.printStackTrace();
        throw e;
    }
}
