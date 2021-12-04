package com.pjatk.awps.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{

    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus) {
        super(message);
        this.message = super.getMessage();
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public ApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = super.getMessage();
        this.throwable = new RuntimeException();
        this.httpStatus = httpStatus;
    }

    public ApiException(String message) {
        super(message);
        this.message = super.getMessage();
        this.throwable = new RuntimeException();
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
