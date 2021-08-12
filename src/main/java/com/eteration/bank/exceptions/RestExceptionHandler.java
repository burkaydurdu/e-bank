package com.eteration.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException exception) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST,
                             exception.getLocalizedMessage(),
                             exception.getLocalizedMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ApiError> insufficientBalance(InsufficientBalanceException exception) {
        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.CONFLICT,
                        exception.getLocalizedMessage(),
                        exception.getLocalizedMessage()
                ),
                HttpStatus.CONFLICT);
    }
}
