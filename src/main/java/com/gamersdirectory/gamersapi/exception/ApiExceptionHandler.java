package com.gamersdirectory.gamersapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice // tells spring that this class will be served for exception handling, allowing to have as many exception handler as wish
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        // 1. create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        // 2. return response entity
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ApiConflictException.class})
    public ResponseEntity<Object> handleApiConflictException(ApiConflictException e) {
        // 1. create payload containing exception details
        HttpStatus conflict = HttpStatus.CONFLICT;

        ApiException apiException = new ApiException(
                e.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        // 2. return response entity
        return new ResponseEntity<>(apiException, conflict);
    }
}
