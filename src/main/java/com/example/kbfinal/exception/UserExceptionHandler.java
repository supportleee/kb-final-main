package com.example.kbfinal.exception;

import com.example.kbfinal.dto.UserErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.kbfinal.exception.UserErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.kbfinal.exception.UserErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserException.class)
    public UserErrorResponse handleException(UserException e, HttpServletRequest request) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getUserErrorCode(), request.getRequestURI(), e.getDetailMessage());
        return UserErrorResponse.builder()
                .errorCode(e.getUserErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public UserErrorResponse handleBadRequest(Exception e, HttpServletRequest request) {
        log.error("url: {}, message: {}",
                request.getRequestURI(), e.getMessage());
        return UserErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public UserErrorResponse handleException(Exception e, HttpServletRequest request) {
        log.error("url: {}, message: {}",
                request.getRequestURI(), e.getMessage());
        return UserErrorResponse.builder()
                .errorCode(INTERNAL_SERVER_ERROR)
                .errorMessage(INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
