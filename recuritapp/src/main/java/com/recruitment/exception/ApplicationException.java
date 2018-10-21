package com.recruitment.exception;

import org.springframework.http.HttpStatus;


public class ApplicationException extends Exception {

    private HttpStatus httpStatus;
    private String message;

    public ApplicationException(final String message, final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
