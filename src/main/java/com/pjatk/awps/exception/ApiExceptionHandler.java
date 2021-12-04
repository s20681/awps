package com.pjatk.awps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class, ApiException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(e.getMessage(), e, httpStatus);
//        return new ResponseEntity<>(apiException, httpStatus);
        return new ResponseEntity<>(apiException.getMessage(), httpStatus);
    }
}
