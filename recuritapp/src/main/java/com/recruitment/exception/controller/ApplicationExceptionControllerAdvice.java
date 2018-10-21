package com.recruitment.exception.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.recruitment.exception.ApplicationException;


@ControllerAdvice
public class ApplicationExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<Object> handleException(ApplicationException applicationException) {
        return handleExceptionInternal(applicationException, applicationException.getMessage(),
                new HttpHeaders(), applicationException.getHttpStatus(), null);
    }

}
